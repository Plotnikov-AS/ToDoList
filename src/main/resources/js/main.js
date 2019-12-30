$function(){
    const appendTask = function(data){
    var taskCode = '<h4>' + data.name + '</h4>' + 'Дата приема задачи: ' + data.date;
    $('#task-list')
        .append('<div>' + taskCode + '</div>');
    };

    //Loading tasks on load page
    $.get('/books', function(response)
    {
        for(i in response) {
            appendTask(response[i]);
        }
    });

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#task-form').css('display', 'flex');
    });
}