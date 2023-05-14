$(document).ready(function() {
    boardListAll();

});


/**
 * 게시글 작성
 * */
function createBoard() {
    console.log($('#title').val());
    console.log($('#content').val());

    axios.post("/board/save", {
        title: $('#title').val(),
        content: $('#content').val(),
    })
        .then(function (response) {
            alert('게시글이 작성되었습니다.')
            location.href= "/";
        })
        .catch(function (error) {
            alert(error);
        });

}

function boardListAll(){

    axios.get("/board/boards")
        .then(function (response) {
            console.log(response)
            let html = '';

            if (response.data.length !==0){
                for(let i = 0; i<response.data.length; i++){
                    // const disabled = dataDate - threeMonthAgo > 0? '' : 'disabled';
                    html += '<tr class="w3-hover-green tableSolid" onclick="location.href=\'/createBoard\'">\n' +
                        '<input type="hidden" id="boardId" value="'+response.data[i].boardId+'"/>\n' +
                        '<input type="hidden" id="correlationBoard" value="'+response.data[i].correlationBoard+'"/>\n' +
                        '<td id="title" style="text-align: center">'+response.data[i].title+'</td>\n' +
                        '<td id="boardDate" style="text-align: center">'+response.data[i].boardDate+'</td>\n'
                    html += '</tr>'
                }
            }else {
                html += '<tr class="w3-hover-green tableSolid" >\n' +
                    '<td colspan="3" style="text-align: center" > 조회된 내용이 없습니다.</td>\n' +
                    '<td style="text-align: center" > </td>\n' +
                    '<td style="text-align: center" > </td>\n' +
                    '</tr>'
            }
            $('#questionTbody').empty();
            $('#questionTbody').append(html)
        })
        .catch(function (error) {
            alert(error);
        });
}




