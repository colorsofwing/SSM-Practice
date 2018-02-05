<%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/2/5
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    var deleteOrderItem = false;
    var deleteOrderItemid = 0;
    $(function () {

        $("a.deleteOrderItem").click(function () {
            deleteOrderItem = false;
            var oiid = $(this).attr("oiid");
            deleteOrderItemid = oiid;
            $("#deleteConfirmModal").modal("show");
        });
        $("button.deleteConfirmButton").click(function () {
            deleteOrderItem = true;
            $("#deleteConfirmModal").modal("hide");
        });

        $("#deleteConfirmModal").on("hidden.bs.modal",function () {
            if(deleteOrderItem==true){
                var page = "foredeleteOrderItem";
                $.post(
                    page,
                    {"oiid":deleteOrderItemid},
                    function (result) {
                        if("success"==result){
                            $("tr.cartProductItemTR[oiid="+deleteOrderItemid+"]").hide();
                        }else {
                            location.href = "login";
                        }
                    }
                );
            }
        });

        $("img.cartProductItemIfSelected").click(function () {
            var selectit = $(this).attr("selectit");
            if("true"==selectit){
                $(this).attr("src","img/site/cartNotSelected.png");
                $(this).attr("selectit","false");
                $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
            }else {
                $(this).attr("src","img/site/cartSelected.png");
                $(this).attr("selectit","true");
                $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
            }
            syncSelect();
            syncCreateOrderButton();
            calcCartSumPriceAndNumber();
        });

        $("img.selectAllItem").click(function () {
            var selectit = $(this).attr("selectit");
            if("true"==selectit){
                $("img.selectAllItem").attr("src","img/site/cartNotSelected.png");
                $("img.selectAllItem").attr("selectit","false");
                $(".cartProductItemIfSelected").each(function () {
                    $(this).attr("src","img/site/cartNotSelected.png");
                    $(this).attr("selectit","false");
                    $(this).parents("tr.cartProductItemTR").css("background-color","#fff");
                });
            }else {
                $("img.selectAllItem").attr("src","img/site/cartSelected.png");
                $("img.selectAllItem").attr("selectit","true");
                $(".cartProductItemIfSelected").each(function () {
                    $(this).attr("src","img/site/cartSelected.png");
                    $(this).attr("selectit","true");
                    $(this).parents("tr.cartProductItemTR").css("background-color","#FFF8E1");
                });
            }
            syncCreateOrderButton();
            calcCartSumPriceAndNumber();
        });

        $(".orderItemNumberSetting").keyup(function () {
            var pid=$(this).attr("pid");
            var stock= $("span.orderItemStock[pid="+pid+"]").text();
            var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
            var num = $(".orderItemNumberSetting[pid="+pid+"]").val();

            num = parseInt(num);
            if(isNaN(num))
                num= 1;
            if(num<=0)
                num = 1;
            if(num>stock)
                num = stock;

            syncPrice(pid,num,price);
        });

        $(".numberPlus").click(function () {
            var pid=$(this).attr("pid");
            var stock= $("span.orderItemStock[pid="+pid+"]").text();
            var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
            var num = $(".orderItemNumberSetting[pid="+pid+"]").val();

            num++;
            if(num>stock)
                num = stock;
            syncPrice(pid,num,price);
        });

        $(".numberMinus").click(function () {
            var pid=$(this).attr("pid");
            var stock= $("span.orderItemStock[pid="+pid+"]").text();
            var price= $("span.orderItemPromotePrice[pid="+pid+"]").text();
            var num = $(".orderItemNumberSetting[pid="+pid+"]").val();

            num--;
            if(num<1)
                num = 1;
            syncPrice(pid,num,price);
        });

        $("button.createOrderButton").click(function () {
            var param = "";
            $(".cartProductItemIfSelected").each(function () {
                if("true"==$(this).attr("selectit")){
                    var oiid = $(this).attr("oiid");
                    param+= "&oiid="+oiid;
                }
            });
            param.substring(1);
            location.href = "forebuy?"+param;
        });
        
        function syncPrice(pid,num,price) {
            $(".orderItemNumberSetting[pid="+pid+"]").val(num);
            var cartProductItemSmallSumPrice = formatMoney(num*price);
            $(".cartProductItemSmallSumPrice[pid="+pid+"]").html("¥"+cartProductItemSmallSumPrice);
            calcCartSumPriceAndNumber();

            var page = "forechangeOrderItem";
            $.post(
                page,
                {"pid":pid,"number":num},
                function(result){
                    if("success"!=result){
                        location.href="login";
                    }
                }
            );
        }

        function syncCreateOrderButton() {
            var selectAny = false;
            $(".cartProductItemIfSelected").each(function () {
                if("true"==$(this).attr("selectit")){
                    selectAny = true;
                }
            });

            if(selectAny){
                $("button.createOrderButton").css("background-color","#C40000");
                $("button.createOrderButton").removeAttr("disabled")
            }else{
                $("button.createOrderButton").css("background-color","#AAAAAA");
                $("button.createOrderButton").attr("disabled","disabled");
            }
        }
        
        function syncSelect() {
            var selectAll = true;
            $(".cartProductItemIfSelected").each(function () {
                if("false"==$(this).attr("selectit")){
                    selectAll = false;
                }
            });

            if(selectAll){
                $("img.selectAllItem").attr("src","img/site/cartSelected.png");
            }else {
                $("img.selectAllItem").attr("src","img/site/cartNotSelected.png");
            }
        }
        
        function calcCartSumPriceAndNumber() {
            var sum = 0;
            var totalNumber = 0;
            $("img.cartProductItemIfSelected[selectit='true']").each(function () {
                var oiid = $(this).attr("oiid");
                var price = $(".cartProductItemSmallSumPrice[oiid="+oiid+"]").text();
                price = price.replace(/,/g, "");
                price = price.replace(/¥/g, "");
                sum+=new Number(price);

                var num = $(".orderItemNumberSetting[oiid="+oiid+"]").val();
                totalNumber+=new Number(num);
            });

            $("span.cartSumPrice").html("¥"+formatMoney(sum));
            $("span.cartTitlePrice").html("¥"+formatMoney(sum));
            $("span.cartSumNumber").html(totalNumber);
        }
    });
</script>

<title>购物车</title>
<div class="cartDiv">
    <div class="cartTitle pull-right">
        <span>已选商品(不含运费)</span>
        <span class="cartTitlePrice">¥0.00</span>
        <button class="createOrderButton" disabled="disabled">结  算</button>
    </div>

    <div class="cartProductList">
        <table class="cartProductTable">
            <thead>
                <tr>
                    <th class="selectAndImage">
                        <img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">全选
                    </th>
                    <th>商品信息</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th width="120px">金额</th>
                    <th class="operation">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ois}" var="oi">
                    <tr class="cartProductItemTR" oiid="${oi.id}">
                        <td>
                            <img selectit="false" oiid="${oi.id}" class="cartProductItemIfSelected" src="img/site/cartNotSelected.png">
                            <a href="#nowhere" style="display: none"><img src="img/site/cartSelected.png"></a>
                            <img class="cartProductImg" src="img/productSingle_middle/${oi.product.imageId}.jpg">
                        </td>
                        <td>
                            <div class="cartProductLinkOutDiv">
                                <a href="foreproduct?pid=${oi.product.id}" class="cartProductLink">${oi.product.name}</a>
                                <div class="cartProductLinkInnerDiv">
                                    <img src="img/site/creditcard.png" title="支持信用卡支付">
                                    <img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
                                    <img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
                                </div>
                            </div>
                        </td>
                        <td>
                            <span class="cartProductItemOringalPrice">¥${oi.product.originalPrice}</span>
                            <span class="cartProductItemPromotionPrice">¥${oi.product.promotePrice}</span>
                        </td>
                        <td>

                            <div class="cartProductChangeNumberDiv">
                                <span class="hidden orderItemStock" pid="${oi.product.id}">${oi.product.stock}</span>
                                <span class="hidden orderItemPromotePrice " pid="${oi.product.id}">${oi.product.promotePrice}</span>
                                <a class="numberMinus" href="#nowhere" pid="${oi.product.id}">-</a>
                                <input class="orderItemNumberSetting" pid="${oi.product.id}" oiid="${oi.id}" autocomplete="off" value="${oi.number}" style="text-align: center">
                                <a class="numberPlus" stock="${oi.product.stock}" pid="${oi.product.id}" href="#nowhere">+</a>
                            </div>

                        </td>
                        <td>
                            <span class="cartProductItemSmallSumPrice" oiid="${oi.id}" pid="${oi.product.id}">
                                ¥<fmt:formatNumber type="number" value="${oi.product.promotePrice*oi.number}" minFractionDigits="2"></fmt:formatNumber>
                            </span>
                        </td>
                        <td>
                            <a class="deleteOrderItem" oiid="${oi.id}" href="#nowhere">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="cartFoot">
        <img selectit="false" class="selectAllItem" src="img/site/cartNotSelected.png">
        <span>全选</span>

        <div class="pull-right">
            <span>已选商品<span class="cartSumNumber">0</span>件</span>
            <span>合计 (不含运费): </span>
            <span class="cartSumPrice" >￥0.00</span>
            <button class="createOrderButton" disabled="disabled">结  算</button>
        </div>
    </div>
</div>