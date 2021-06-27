// Mozilla, Opera, Webkit 
if ( document.addEventListener ) {
    document.addEventListener( "DOMContentLoaded", function(){
      document.removeEventListener( "DOMContentLoaded", arguments.callee, false);
      initDemo(); //�곕え瑜� �꾪븳 �ㅽ겕由쏀듃
      //try{
          documentReady();
          loadScroll();
      //}catch(e) {};
          //loadScroll();
    }, false );
  
  // If IE event model is used
  } else if ( document.attachEvent ) {
    // ensure firing before onload
    document.attachEvent("onreadystatechange", function(){
      if ( document.readyState === "complete" ) {
        document.detachEvent( "onreadystatechange", arguments.callee );
        initDemo(); //�곕え瑜� �꾪븳 �ㅽ겕由쏀듃
        //try{
            documentReady();
            loadScroll();
      //  }catch(e) {};
        //	loadScroll();
     }
    });
  }
  
  function loadScroll() {
      var navWrapper = document.getElementById("nav-wrapper");
      if(!navWrapper)
          return;
      navWrapper.scrollTop = Number(_getParam("s"));
  
  };
  
  var _getParam = function(key){
      var _parammap = {};
      document.location.search.replace(/\??(?:([^=]+)=([^&]*)&?)/g, function () {
          function decode(s) {
              return decodeURIComponent(s.split("+").join(" "));
          }
          _parammap[decode(arguments[1])] = decode(arguments[2]);
      });
      return _parammap[key];
  };
  
  // 紐⑤컮�� �붾㈃�� 寃쎌슦 硫붾돱 異쒕젰
  var menuDisplayed = false;
  function mobileBtnClick() {
      if(!menuDisplayed) {
          menuDisplayed = true;
          document.getElementById("nav-wrapper").style.display = "block";
      } else {
          menuDisplayed = false;
          document.getElementById("nav-wrapper").style.display = "none";
      }
  };
      
  // �곕え 硫붾돱 �≫떚釉� �ㅽ��� 留뚮뱾湲�
  function initDemo() {
      var url = location.href;
      var idx = url.indexOf("?");
      var lastIdx = url.indexOf("&");
      var menuItem;
      if(idx > 0) {
          var link = url.substring(idx+1, lastIdx);
          if(link && link.length > 2) {
              menuItem = document.getElementById(link);
              if(menuItem) {
                  menuItem.className = menuItem.className + " active";
              }
          }
      }
      
      // theme ���됲듃 �좏깮
      var select = document.getElementById("themeSelect");
      if(!select)
          return;
      
      switch(theme) {
      case "modern":
          select.selectedIndex = 1;
          break;
      case "modern_red":
          select.selectedIndex = 2;
          break;
      case "classic":	
          select.selectedIndex = 3;
          break;
      case "dark":
          select.selectedIndex = 4;
          break;
      case "blue":
          select.selectedIndex = 5;
          break;
      case "default":
      default:
          select.selectedIndex = 0;
      }
  };
  
  function getScroll() {
      var scrollTop  = 0;
      var navWrapper = document.getElementById("nav-wrapper");
      if(!navWrapper)
          return 0;
      scrollTop =  navWrapper.scrollTop || 0;
      return scrollTop;
  };
  
  function changeLocation(href, scrollPos) {
      if(scrollPos === undefined) {
          scrollPos = getScroll();
      }
      location.href = href + "&theme=" + theme + "&s=" + scrollPos;
  };
  
  function themeSelectHandler(event) {
      var select = document.getElementById("themeSelect");
      theme = select.value;
      
      var url = location.href;
      var idx = url.indexOf("&");
      var link = url.substring(0, idx);
      
      changeLocation(link);
  };