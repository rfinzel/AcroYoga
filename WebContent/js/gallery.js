$(document).ready(function(){
    //FANCYBOX
	
    //https://github.com/fancyapps/fancyBox
    $(".fancybox").fancybox({
        openEffect: "none",
        closeEffect: "none",
        padding:0, margin:0,
        
        beforeShow: function () {
        $("body *:not(.fancybox-overlay, .fancybox-overlay *)").addClass("blur");
        },
        afterClose: function () {
            $("body *:not(.fancybox-overlay, .fancybox-overlay *)").removeClass("blur");
        }
        
    });
    
    
});

   