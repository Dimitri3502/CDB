/**
 * 
 */

//On load
(function ( $ ) {

    // Click on discontinued
    $("#introduced").change(function() {
    	if ($("#introduced").val()!=0){
    		$("#discontinued").prop('min',$("#introduced").val());
    	}
    });

}( jQuery ));