const calculateSeperateItem = (item, action) =>{
    const input = item.querySelector('.input');
    switch (action){
        case "minus":{
            input.value--;
            break;
        }
        case "plus":{
            input.value++;
            break;
        }
    }
}




document.getElementById("menu-list").addEventListener("click", (event) =>{
    const input = event.target.closest(".item").querySelector(".input");
    if (Number(input.value) != 1) {
        if (event.target.classList.contains("minus")) {
            calculateSeperateItem(
                event.target.closest(".item"),
                "minus"
            );
        }
    }
    if (event.target.classList.contains("plus")) {
        calculateSeperateItem(
            event.target.closest(".item"),
           "plus"
        );
    }
});