const calculateSeperateItem =(cartItem, action) => {
    const input = cartItem.querySelector('.input');
    switch(action){
        case "minus":{
            input.value--;
            totalPrice.dataset.value = Number(totalPrice.dataset.value) - Number(input.dataset.price);
            break;
        }
        case "plus":{
            input.value++;
            totalPrice.dataset.value = Number(totalPrice.dataset.value) + Number(input.dataset.price);
            break;
        }
    }
    totalPrice.textContent = round(Number(totalPrice.dataset.value),2);
    cartItem.querySelector(".subTotal").textContent = round(getSubTotalPrice(input),2);
};



document.getElementById('cart').addEventListener('click', (event) =>{
    const input = event.target.closest('.cartItem').querySelector('.input');
    if (Number(input.value) != 1){
        if (event.target.classList.contains("minus")){
            calculateSeperateItem(
                event.target.closest('.cartItem'),
                'minus'
            );
            console.log("minus");
        }
    }

    if (event.target.classList.contains("plus")){
        calculateSeperateItem(
            event.target.closest('.cartItem'),
            'plus'
        );
    }
});

const getSubTotalPrice = (input) =>Number(input.dataset.price)*Number(input.value);

const totalPrice = document.getElementById("totalPrice");

const init = () =>{
    let totalCost = 0;
    [...document.querySelectorAll(".cartItem")].forEach((cartItem) => {
        totalCost += getSubTotalPrice(cartItem.querySelector('.input'));
    })

    totalPrice.textContent = round(totalCost, 2);
    totalPrice.dataset.value = round(totalCost, 2);
};

const round = (number, decimalPlaces) => {
    const factorOfTen = Math.pow(10, decimalPlaces)
    return Math.round(number * factorOfTen) / factorOfTen
};


init();
