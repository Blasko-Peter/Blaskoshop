function addItem(){
    let addButtons = document.getElementsByClassName("addButton");
    for(let addButton of addButtons){
        addButton.addEventListener("click", function () {
            let itemId = addButton.getAttribute("id");
            let params = {id: itemId};
            $.post('/shop', $.param(params), function () {
                console.log("successful");
            });
        });
    }
}

addItem();
