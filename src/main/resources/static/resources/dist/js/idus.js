function reGenerateOptionNumber() {
    var select_group__body = document.getElementById('select_group__body');
    var olList = select_group__body.getElementsByTagName('ol');

    for (var i = 0; i < olList.length; i++) {
        var option_input = olList[i].getElementsByTagName('li')[0].getElementsByTagName('input')[0];
        option_input.setAttribute('name', 'optionDTOList[' + i + '].title');
        var ulTag = olList[i].getElementsByTagName('ul')[0];
        var inputList = ulTag.getElementsByTagName('input');
        for (var j = 0; j < inputList.length; j++) {
            if(j%2==0){
                inputList[j].setAttribute('name', 'optionDTOList[' + i + '].optionSelectDTOList[' + Math.floor(j / 2) + '].title');
            } else {
                inputList[j].setAttribute('name', 'optionDTOList[' + i + '].optionSelectDTOList[' + Math.floor(j / 2) + '].price');
            }
        }
    }
}

function showOption() {
    var optionScrollable = document.getElementById('optionScrollable');
    optionScrollable.setAttribute('style', '');

    // 옵션셀렉트 다시 열림.
    document.getElementsByClassName('select_group__parent_list')[0].setAttribute('class','select_group__parent_list active');
    document.getElementsByClassName('select_group__child_list')[0].setAttribute('class', 'select_group__child_list active');


}

function hideOption() {
    var optionScrollable = document.getElementById('optionScrollable');
    optionScrollable.setAttribute('style', 'display: none;');

}


function addOption() {
    var select_group__body = document.getElementById('select_group__body');

    select_group__body.innerHTML = select_group__body.innerHTML + `<br>
                <ol data-v-de09a10e="" class="select_group__parent_list active">
                    <li data-v-de09a10e="">
                        <span data-v-de09a10e="">
                            <input type="text" name="optionDTOList[1]" placeholder="옵션">
                        </span>
                        <span data-v-de09a10e="" class="align_right">
                            <button onclick="addOptionSelect(this, event)" data-v-de09a10e="" class="idus-icon-arrow-down">
                            </button> 
                            <button onclick="deleteOption(this)" data-v-de09a10e="" class="idus-icon-close">
                            </button>
                        </span>          
                    </li>
                                                
                    <div data-v-de09a10e="" class="bottom-border full"></div>
                    
                    <ul data-v-de09a10e="" class="select_group__child_list active">
                        <li data-v-de09a10e="">
                            <span data-v-de09a10e="">
                                <input type="text" name="selectOption" placeholder="선택">
                                <input type="text" name="optionDTOList[0].optionSelectDTOList[0].price" placeholder="추가금액">
                            </span>
                             <button onclick="" data-v-de09a10e="" class="idus-icon-close"></button>
                        </li>
                   </ul>
                </ol>`;
    reGenerateOptionNumber();
}

function addOptionSelect(buttonTag, event) {
    event.preventDefault();
    var ulTag = buttonTag.parentNode.parentNode.parentNode.getElementsByTagName('ul')[0];
    ulTag.innerHTML = ulTag.innerHTML + `<li><span><input type="text" name="" placeholder="선택"></span><input type="text" name="optionDTOList[0].optionSelectDTOList[0].price" placeholder="추가금액"><button onclick="" data-v-de09a10e="" class="idus-icon-close"></button></li>`;
    reGenerateOptionNumber();

}

function deleteOption(buttonTag) {
    buttonTag.parentNode.parentNode.previousSibling.remove();
    buttonTag.parentNode.parentNode.remove();
    reGenerateOptionNumber();
}

function deleteOptionforUpdate(buttonTag) {
    buttonTag.parentNode.parentNode.previousSibling.remove();
    buttonTag.parentNode.parentNode.remove();
    // reGenerateOptionNumber();
}

function deleteOptionSelectforUpdate(buttonTag) {
    buttonTag.parentNode.remove();
    var idx = buttonTag.getAttribute('optionselectidx');
    console.log(idx);
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200 ) {
            console.log(httpRequest.responseText);
        }
    };
    // POST 방식의 요청은 데이터를 Http 헤더에 포함시켜 전송함.
    httpRequest.open("POST", "/optionSelect/delete", true);
    httpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    httpRequest.send("idx="+idx);
}