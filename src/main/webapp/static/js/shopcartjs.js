function addItemSC(){
    let addButtonsSC = document.getElementsByClassName("addButtonSC");
    for(let addButtonSC of addButtonsSC){
        addButtonSC.addEventListener("click", function () {
            let itemId = addButtonSC.getAttribute("id");
            let itemsvalue = document.getElementById("itemsvalue" + itemId);
            let itemprice = document.getElementById("itemprice" + itemId);
            let itemallprice = document.getElementById("itemallprice" + itemId);
            let price = parseInt(itemprice.innerHTML);
            let totalprice = document.getElementById("totalprice");
            let newTotalPrice = parseInt(totalprice.innerHTML) + price;
            totalprice.innerHTML = parseFloat(newTotalPrice.toString()).toFixed(1) + " USD";
            let number = parseInt(itemsvalue.innerHTML) + 1;
            itemsvalue.innerHTML = number;
            let allprice = parseInt(itemallprice.innerHTML) + price;
            itemallprice.innerHTML = parseFloat(allprice.toString()).toFixed(1) + " USD";
            let params = {id: itemId};
            $.post('/shop', $.param(params), function () {
                console.log("successful");
            });
        });
    }
}

function minusItemSC(){
    let minusButtonsSC = document.getElementsByClassName("minusButtonSC");
    for(let minusButtonSC of minusButtonsSC){
        minusButtonSC.addEventListener("click", function () {
            let itemId = minusButtonSC.getAttribute("id");
            let itemsvalue = document.getElementById("itemsvalue" + itemId);
            let itemprice = document.getElementById("itemprice" + itemId);
            let itemallprice = document.getElementById("itemallprice" + itemId);
            let price = parseInt(itemprice.innerHTML);
            let totalprice = document.getElementById("totalprice");
            let newTotalPrice = parseInt(totalprice.innerHTML) - price;
            totalprice.innerHTML = parseFloat(newTotalPrice.toString()).toFixed(1) + " USD";
            let number = parseInt(itemsvalue.innerHTML) - 1;
            if( number == 0){
                minusButtonSC.parentNode.parentNode.parentNode.removeChild(minusButtonSC.parentNode.parentNode);
            } else {
                itemsvalue.innerHTML = number;
                let allprice = parseInt(itemallprice.innerHTML) - price;
                itemallprice.innerHTML = parseFloat(allprice.toString()).toFixed(1) + " USD";
            }
            let params = {id: itemId, number: number};
            $.post('/shopcart', $.param(params), function () {
                console.log("successful");
            });
        });
    }
}

function deleteItem(){
    let deleteButtons = document.getElementsByClassName("deleteButton");
    for(let deleteButton of deleteButtons){
        deleteButton.addEventListener("click", function () {
            let itemId = deleteButton.getAttribute("id");
            let itemprice = document.getElementById("itemprice" + itemId);
            let price = parseInt(itemprice.innerHTML);
            let itemsvalue = document.getElementById("itemsvalue" + itemId);
            let productNumber = parseInt(itemsvalue.innerHTML);
            let minusAllPrice = price * productNumber;
            let totalprice = document.getElementById("totalprice");
            let newTotalPrice = parseInt(totalprice.innerHTML) - minusAllPrice;
            totalprice.innerHTML = parseFloat(newTotalPrice.toString()).toFixed(1) + " USD";
            deleteButton.parentNode.parentNode.parentNode.removeChild(deleteButton.parentNode.parentNode);
            let params = {id: itemId, number: "0"};
            $.post('/shopcart', $.param(params), function () {
                console.log("successful");
            });
        });
    }
}

addItemSC();
minusItemSC();
deleteItem();