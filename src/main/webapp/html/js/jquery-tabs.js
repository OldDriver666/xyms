function resetTabs(){
    $("#content > div").hide(); //Hide all content
    $("#tabs a").attr("id",""); //Reset id's      
}
var myUrl = window.location.href; //get URL
var myUrlTab = myUrl.substring(myUrl.indexOf("#")); 
var myUrlTabName = myUrlTab.substring(0,4); 
(function(){
    $("#content > div").hide(); // Initially hide all content
    $("#tabs li:first a").attr("class","current"); // Activate first tab
    $("#content > div:first").fadeIn(); // Show first tab content
    
    $("#tabs a").on("click",function(e) {
        e.preventDefault();
        if ($(this).attr("class") == "current"){ //detection for current tab
         return       
        }
        else{             
        resetTabs();
        $(this).attr("class","current"); // Activate this
        $($(this).attr('name')).fadeIn(); // Show content for current tab
        }
    });
    for (i = 1; i <= $("#tabs li").length; i++) {
      if (myUrlTab == myUrlTabName + i) {
          resetTabs();
          $("a[name='"+myUrlTab+"']").attr("class","current"); // Activate url tab
          $(myUrlTab).fadeIn(); // Show url tab content        
      }
    }
})()