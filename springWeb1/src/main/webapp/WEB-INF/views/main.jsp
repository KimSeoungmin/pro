<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <style type="text/css">
    body {
      width: 960px;
      height: 500px;
      position: relative;
    }
    #map {
      width: 100%;
      height: 100%;
    }
    </style>
    <title>2D 지도 서비스 2.0 마커레이어 커스터마이징</title>
  </head>
  <body>
    <div id="map" style="height: 400px;"></div>
    <form id="searchForm" action="#" class="form_data" onsubmit="return false;search();">
        <input type="hidden" name="page" value="1" />
        <input type="hidden" name="type" value="PLACE" />
        <input type="hidden" name="request" value="search" />
        <input type="hidden" name="apiKey" value="0B951D11-29FF-3009-A0F6-51B7C238BFCD" />
         
        <div>
            <input type="text"  id="searchValue" name="query" value="야탑역" style="width: 100px;" /> <a href="javascript:search();" >검색</a> 
        </div>
    </form>
     
    <script type="text/javascript" src="http://map.vworld.kr/js/vworldMapInit.js.do?version=2.0&apiKey=0B951D11-29FF-3009-A0F6-51B7C238BFCD"></script>
    <script src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    <script type="text/javascript">
        vw.ol3.MapOptions = {
            basemapType : vw.ol3.BasemapType.PHOTO,
            controlDensity : vw.ol3.DensityType.BASIC,
            interactionDensity : vw.ol3.DensityType.FULL,
            controlsAutoArrange : true,
            homePosition : vw.ol3.CameraPosition,
            initPosition : vw.ol3.CameraPosition,
        };
        map = new vw.ol3.Map("map", vw.ol3.MapOptions);
         
        var features = new Array();
        var styleCache = new Array();
        var search = function(){
            $.ajax({
                type: "get",
                url: "http://api.vworld.kr/req/search",
                data : $('#searchForm').serialize(),
                dataType: 'jsonp',
                async: false,
                success: function(data) {
                    for(var o in data.response.result.items){ 
                        if(o==0){
                            move(data.response.result.items[o].point.x*1,data.response.result.items[o].point.y*1);
                        }
                        //Feature 객체에 저장하여 활용 
                        features[o] = new ol.Feature({
                            geometry: new ol.geom.Point(ol.proj.transform([ data.response.result.items[o].point.x*1,data.response.result.items[o].point.y*1],'EPSG:4326', "EPSG:900913")),
                            title: data.response.result.items[o].title,
                            parcel: data.response.result.items[o].address.parcel,
                            road: data.response.result.items[o].address.road,
                            category: data.response.result.items[o].category,
                            point: data.response.result.items[o].point
                        });
                        features[o].set("id",data.response.result.items[o].id);
                         
                        var iconStyle = new ol.style.Style({
                            image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
                                anchor: [0.5, 46],
                                anchorXUnits: 'fraction',
                                anchorYUnits: 'pixels',
                                src: 'http://map.vworld.kr/images/ol3/marker_blue.png'
                            }))
                        });
                        features[o].setStyle(iconStyle);
                         
                    }
                     
                    var vectorSource = new ol.source.Vector({
                          features: features
                    });
                    /*
                        기존검색결과를 제거하기 위해 키 값 생성
                    */
                    var vectorLayer = new ol.layer.Vector({
                        source: vectorSource
                    });
                     
                    /*
                        기존검색결과를 제거하기 위해 키 값 생성
                    */
                    vectorLayer.set("vectorLayer","search_vector")
                     
                    map.getLayers().forEach(function(layer){
                        if(layer.get("vectorLayer")=="search_vector"){
                            map.removeLayer(layer);
                        }
                    });
                     
                    map.addLayer(vectorLayer);
                },
                error: function(xhr, stat, err) {}
            });
        }
         
        var move = function(x,y){//127.10153, 37.402566
            map.getView().setCenter(ol.proj.transform([ x, y ],'EPSG:4326', "EPSG:900913")); // 지도 이동
            map.getView().setZoom(12);
        }
         
        /* 클릭 이벤트 제어 */
        map.on("click", function(evt) {
            var coordinate = evt.coordinate //좌표정보
            var pixel = evt.pixel
            var cluster_features = [];
            var features = [];
             
            //선택한 픽셀정보로  feature 체크 
            map.forEachFeatureAtPixel(pixel, function(feature, layer) {
                var title = feature.get("title");
                if(title.length>0){
                     
                    var overlayElement= document.createElement("div"); // 오버레이 팝업설정 
                     
                    overlayElement.setAttribute("class", "overlayElement");
                    overlayElement.setAttribute("style", "background-color: #3399CC; border: 2px solid white; color:white");
                    overlayElement.setAttribute("onclick", "deleteOverlay('"+feature.get("id")+"')");
                    overlayElement.innerHTML="<p>"+title+"</p>";
                     
                    var overlayInfo = new ol.Overlay({
                        id:feature.get("id"),
                        element:overlayElement,
                        offset: [0, -70],
                        position: ol.proj.transform([feature.get("point").x*1, feature.get("point").y*1],'EPSG:4326', "EPSG:900913")
                    });
                     
                    if(feature.get("id") != null){
                        map.removeOverlay(map.getOverlayById(feature.get("id")));
                    }
                     
                    map.addOverlay(overlayInfo);
                }
            });
        });
         
        /**
            오버레이 삭제
        */
        var deleteOverlay = function(id){
            map.removeOverlay(map.getOverlayById(id));
        }
         
    </script>
  </body>