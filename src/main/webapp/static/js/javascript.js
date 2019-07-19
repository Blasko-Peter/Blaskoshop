function addItem(){
    let addButtons = document.getElementsByClassName("addButton");
    let itemsnumber = document.getElementById("itemsnumber");
    for(let addButton of addButtons){
        addButton.addEventListener("click", function () {
            let number = parseInt(itemsnumber.innerHTML) + 1;
            itemsnumber.innerHTML = number;
            let itemId = addButton.getAttribute("id");
            let params = {id: itemId};
            $.post('/shop', $.param(params), function () {
                console.log("successful");
            });
        });
    }
}

addItem();
