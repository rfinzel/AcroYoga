(function($) {
    "use strict"; // Start of use strict
    var images = 1;
    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $(document).on('click', 'a.page-scroll', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });

    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function() {
        $('.navbar-toggle:visible').click();
    });

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

    // Initialize and Configure Scroll Reveal Animation
    window.sr = ScrollReveal();
    sr.reveal('.sr-icons', {
        duration: 600,
        scale: 0.3,
        distance: '0px'
    }, 200);
    sr.reveal('.sr-button', {
        duration: 1000,
        delay: 200
    });
    sr.reveal('.sr-contact', {
        duration: 600,
        scale: 0.3,
        distance: '0px'
    }, 300);

    // Initialize and Configure Magnific Popup Lightbox Plugin
    $('.popup-gallery').magnificPopup({
        delegate: 'a',
        type: 'image',
        tLoading: 'Loading image #%curr%...',
        mainClass: 'mfp-img-mobile',
        gallery: {
            enabled: true,
            navigateByImgClick: true,
            preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
        },
        image: {
            tError: '<a href="%url%">The image #%curr%</a> could not be loaded.'
        }
    });
    
    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	$('#addImage').click(function(e) {
		$("#images").append("<input type=\"file\" name=\"file" + ++images + "\"/>");
		
		$("#amount").val(images);
	});
	
	$(document).ready(function(e){
	    $('.search-panel .dropdown-menu').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-panel span#search_concept').text(concept);
			$('.input-group #search_param').val(param);
		});
	});
	
	 $('#updateAccount-link').click(function(e) {
			$("#updateForm").delay(100).fadeIn(100);
	 		$("#showAccount").fadeOut(100);
	 		$("#deleteForm").fadeOut(100);
			$('#showAccount').removeClass('form-active');		
			$('#showAccount').addClass('form-notactive');
			$('#deleteAccount').removeClass('form-active');		
			$('#deleteAccount').addClass('form-notactive');
			$('#updateForm').removeClass('form-notactive');		
			$('#updateForm').addClass('form-active');
			e.preventDefault();
		});
	 
		$('#changeAccount-link').click(function(e) {
			$("#showAccount").delay(100).fadeIn(100);
	 		$("#updateForm").fadeOut(100);
	 		$("#deleteForm").fadeOut(100);
			$('#updateForm').removeClass('form-active');		
			$('#updateForm').addClass('form-notactive');
			$('#deleteAccount').removeClass('form-active');		
			$('#deleteAccount').addClass('form-notactive');
			$('#showAccount').removeClass('form-notactive');		
			$('#showAccount').addClass('form-active');
			//e.preventDefault();
		});
		
		$('#deleteAccount-link').click(function(e) {
			$("#deleteAccount").delay(100).fadeIn(100);
	 		$("#updateForm").fadeOut(100);
	 		$("#showAccount").fadeOut(100);
			$('#updateForm').removeClass('form-active');		
			$('#updateForm').addClass('form-notactive');
			$('#showAccount').removeClass('form-active');		
			$('#showAccount').addClass('form-notactive');
			$('#deleteAccount').removeClass('form-notactive');		
			$('#deleteAccount').addClass('form-active');
			//e.preventDefault();
		});
	
	$(document).ready(function(){

	    
	    $("[data-toggle=tooltip]").tooltip();
	});
	
	
})(jQuery); // End of use strict
