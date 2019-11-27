$(function() {

      appendToDo = function(data) {
       var todoCode = '<a href="#" class="toDo-link" data-id="' + data.id + '">' + data.name + '</a><br>';
       $('#ToDo list').append('<div>' + todoCode + '</div>');
     };

     //
     $.get('/books/', function(response) {
        for(i in response) {
        appendToDo(response[i]);
        }
     });

     //
     $('#add-new-list').click(function() {
          $('#ToDo-form').css('display', 'flex');
      });

     //
     $('#ToDo-form').click(function(event) {
        if(event.target === this) {
           $(this).css('display', 'none');
        }
     });

     $(document).on('click', '.toDo-link', function() {
         var link = $(this);
         var toDoId = link.data('id');
         $.ajax({
                    method: "GET",
                    url: '/books/' + toDoId,
                    //data: data,
                    success: function(response) {
                       var code = '<span>Год выпуска ' + response.details + '</span>';
                       link.parent().append(code);
                    },
                    error: function(response) {
                        if(response.status == 404) {
                           alert('Книга не найдена!');
                        }
                    }
                 });
                 return false;
     });

     //
     $('#Save-list').click(function() {
        var data = $('ToDo-form form').serialize();
        $.ajax({
           method: "POST",
           url: '/books/',
           //data: data,
           success: function(response) {
              $('ToDo-form').css('display', 'none');
              var todo = {};
              todo.id = response;
              var dataArray = $('#ToDo-form form').serializeArray();

                for(i in dataArray) {
                   todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendToDo(todo);
           }
        });
        return false;
     });

})