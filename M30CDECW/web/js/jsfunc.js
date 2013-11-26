/* 
 *  This method works for updating, while select a product type
 *  from combo box it goes to productlist servlet with argument of the
 *  selected data
 */

$('.upItemType').change(function() {
    $('.upItemName').empty();
    $.ajax({
        url: 'http://localhost:8080/M30CDECW/ProductList?action=ViewList',
        data: {selectedItemType: $(this).val()},
        success: function(response) {
            $('.upItemName').append(response);
        }
    });
});


/* 
 * This is for view product on change of product name combo box
 */

$('.upItemName').change(function() {
    $.ajax({
        url: 'http://localhost:8080/M30CDECW/ProductList?action=ViewDetails',
        data: {selectedItemType: $('.upItemType').val(), selectedItemName: $(this).val()},
        success: function(response) {
            $('#details').append(response);
        }
    });

});


/* 
 * 
 */


$('.delProductType').change(function() {
    $('.delProductName').empty();
    $.ajax({
        url: 'http://localhost:8080/M30CDECW/ProductList?action=ViewList',
        data: {selectedItemType: $(this).val()},
        success: function(response) {
            $('.delProductName').append(response);
        }
    });
});



