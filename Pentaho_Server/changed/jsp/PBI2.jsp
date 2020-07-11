<%@ page 
	session="true"
	contentType="text/html;"
	import="java.util.*, sad.PluginDoPentaho"
%>
<html>

<head>
  <style>
    HTML {
      margin: 0 !important;
      border: none !important;
    }

    .dragdrop-handle {
      cursor: move;
      user-select: none;
      -khtml-user-select: none;
      -moz-user-select: none;
    }

    .dragdrop-draggable {
      zoom: 1;
    }

    .dragdrop-dragging {
      zoom: normal;
    }

    .dragdrop-positioner {
      border: 1px dashed #1e90ff;
      margin: 0 !important;
      zoom: 1;
      z-index: 100;
    }

    .dragdrop-flow-panel-positioner {
      color: #1e90ff;
      display: inline;
      text-align: center;
      vertical-align: middle;
    }

    .dragdrop-proxy {
      background-color: #7af;
    }

    .dragdrop-selected,
    .dragdrop-dragging,
    .dragdrop-proxy {
      filter: alpha(opacity \= 30);
      opacity: 0.3;
    }

    .dragdrop-movable-panel {
      z-index: 200;
      margin: 0 !important;
      border: none !important;
    }
  </style>
  <style type="text/css">
    @charset "UTF-8";

    [ng\:cloak],
    [ng-cloak],
    [data-ng-cloak],
    [x-ng-cloak],
    .ng-cloak,
    .x-ng-cloak,
    .ng-hide:not(.ng-hide-animate) {
      display: none !important;
    }

    ng\:form {
      display: block;
    }

    .ng-animate-shim {
      visibility: hidden;
    }

    .ng-anchor {
      position: absolute;
    }
  </style>
  <style>
    HTML {
      margin: 0 !important;
      border: none !important;
    }

    .dragdrop-handle {
      cursor: move;
      user-select: none;
      -khtml-user-select: none;
      -moz-user-select: none;
    }

    .dragdrop-draggable {
      zoom: 1;
    }

    .dragdrop-dragging {
      zoom: normal;
    }

    .dragdrop-positioner {
      border: 1px dashed #1e90ff;
      margin: 0 !important;
      zoom: 1;
      z-index: 100;
    }

    .dragdrop-flow-panel-positioner {
      color: #1e90ff;
      display: inline;
      text-align: center;
      vertical-align: middle;
    }

    .dragdrop-proxy {
      background-color: #7af;
    }

    .dragdrop-selected,
    .dragdrop-dragging,
    .dragdrop-proxy {
      filter: alpha(opacity \= 30);
      opacity: 0.3;
    }

    .dragdrop-movable-panel {
      z-index: 200;
      margin: 0 !important;
      border: none !important;
    }
  </style>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Pentaho PBI Console</title>


  <meta name="gwt:property" content="locale=pt_PT">
  <link rel="icon" href="/pentaho-style/favicon.ico">
  <link rel="apple-touch-icon" sizes="180x180" href="/pentaho-style/apple-touch-icon.png">
  <link rel="icon" type="image/png" sizes="32x32" href="/pentaho-style/favicon-32x32.png">
  <link rel="icon" type="image/png" sizes="16x16" href="/pentaho-style/favicon-16x16.png">
  <link rel="mask-icon" href="/pentaho-style/safari-pinned-tab.svg" color="#cc0000">
  <link rel="stylesheet" href="mantle/MantleStyle.css">

  <link rel="stylesheet" href="content/data-access/resources/gwt/datasourceEditorDialog.css">

  <link rel="stylesheet" href="mantle/Widgets.css">

  <!-- ANGULAR INCLUDES -->
  <link rel="stylesheet" href="content/common-ui/resources/themes/css/angular-animations.css">
  <script type="text/javascript" src="/pentaho/js/themeResources.js"></script>
  <script language="javascript" type="text/javascript" src="webcontext.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/reporting/reportviewer/reporting-require-js-cfg.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/pentaho-cdf-dd/js/cde-require-js-cfg.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/pentaho-cdf/js/cdf-require-js-cfg.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/config/deploy/client-config-enabler-require-js-cfg.js?context=mantle"></script>
  <script language="javascript" type="text/javascript" src="/pentaho/js/require-js-cfg.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/common-ui-require-js-cfg.js?context=mantle"></script>
  <script type="text/javascript" src="/pentaho/content/common-ui/resources/web/require.js"></script>
  <script type="text/javascript" src="/pentaho/content/common-ui/resources/web/require-cfg.js"></script>
  <script type="text/javascript" src="/pentaho/osgi/requirejs-manager/js/require-init.js?requirejs=false"></script>
  <script language="javascript" type="text/javascript" src="/pentaho/js/themes.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/themes/jquery.js?context=mantle"></script>
  <link rel="stylesheet" type="text/css"
    href="/pentaho/content/data-access/resources/gwt/datasourceEditorDialog.css?context=mantle">
  <link rel="stylesheet" type="text/css"
    href="/pentaho/content/data-access/resources/gwt/datasourceAdminDialog.css?context=mantle">
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/dojo/djConfig.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/cache/cache-service.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/themes/jquery.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/themes/themeUtils.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/util/URLEncoder.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/util/SessionExpiryCheckStartingPoint.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/util/pentaho-csrf.js?context=mantle"></script>
  <script language="javascript" type="text/javascript"
    src="/pentaho/content/common-ui/resources/web/repo/pentaho-ajax.js?context=mantle"></script>

  <script type="text/javascript" src="mantle/nativeScripts.js"></script>
  <script type="text/javascript">
    try {
      if (window.opener && window.opener.reportWindowOpened != undefined) {
        window.opener.reportWindowOpened();
      }
    } catch (/* XSS */ ignored) { }

    var dataAccessAvailable = false; //Used by child iframes to tell if data access is available.
    /* this function is called by the gwt code when initing, if the user has permission */
    function initDataAccess(hasAccess) {
      dataAccessAvailable = hasAccess;
      if (!hasAccess) {
        return;
      }
      if (typeof (addMenuItem) == "undefined") {
        setTimeout("initDataAccess(" + hasAccess + ")", 1000);
        return;
      } else {
        addMenuItem("manageDatasourcesEllipsis", "manage_content_menu", "ManageDatasourcesCommand");
        addMenuItem("newDatasource", "new_menu", "AddDatasourceCommand");
      }
    }

    var datasourceEditorCallback = {
      onFinish: function (val, transport) {
      },
      onError: function (val) {
        alert('error:' + val);
      },
      onCancel: function () {
      },
      onReady: function () {
      }
    }

    // This allows content panels to have PUC create new datasources. The iframe requesting
    // the new datasource must have a function "openDatasourceEditorCallback" on it's window scope
    // to be notified of the successful creation of the datasource.
    function openDatasourceEditorIFrameProxy(windowReference) {
      var callbackHelper = function (bool, transport) {
        windowReference.openDatasourceEditorCallback(bool, transport);
      }
      pho.openDatasourceEditor(new function () {
        this.onError = function (err) {
          alert(err);
        }
        this.onCancel = function () {
        }
        this.onReady = function () {
        }
        this.onFinish = function (bool, transport) {
          callbackHelper(bool, transport);
        }
      });
    }

    // Require Angular Plugin Initialization
    require(['mantle/puc-api/pucAngularApi']);
  </script>

  <link rel="stylesheet" href="http://127.0.0.1:8080/pentaho/mantle/xul.css">
  <link rel="stylesheet"
    href="http://127.0.0.1:8080/pentaho/content/data-access/resources/gwt/databaseEditorDialog.css">
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="mantle/puc-api/pucAngularApi" src="/pentaho/mantle/puc-api/pucAngularApi.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/util/BusyIndicator"
    src="/pentaho/content/common-ui/resources/web/compressed/util/BusyIndicator.js"></script>
  <link rel="stylesheet" type="text/css" href="/pentaho/content/common-ui/resources/themes/ruby/globalRuby.css">
  <link rel="stylesheet" type="text/css"
    href="/pentaho/content/common-ui/resources/themes/ruby/bootstrap/css/bootstrap-namespaced.css">
  <link rel="stylesheet" type="text/css" href="/pentaho/mantle/themes/ruby/mantleRuby.css">
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/AnimatedAngularPluginHandler"
    src="/pentaho/content/common-ui/resources/web/compressed/plugin-handler/animatedAngularPluginHandler.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/underscore"
    src="/pentaho/content/common-ui/resources/web/compressed/underscore/underscore.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/util/Glasspane"
    src="/pentaho/content/common-ui/resources/web/compressed/util/Glasspane.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/util/PentahoSpinner"
    src="/pentaho/content/common-ui/resources/web/compressed/util/PentahoSpinner.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/util/spin"
    src="/pentaho/content/common-ui/resources/web/compressed/util/spin.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/util/tripleclick"
    src="/pentaho/content/common-ui/resources/web/compressed/util/tripleclick.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_" data-requiremodule="common-ui/jquery"
    src="/pentaho/content/common-ui/resources/web/compressed/jquery/jquery.conflict.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_" data-requiremodule="common-ui/ring"
    src="/pentaho/content/common-ui/resources/web/compressed/ring/ring.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/AngularPluginHandler"
    src="/pentaho/content/common-ui/resources/web/compressed/plugin-handler/angularPluginHandler.js"></script>
  <style type="text/css"></style>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/jquery-clean"
    src="/pentaho/content/common-ui/resources/web/compressed/jquery/jquery.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_" data-requiremodule="common-ui/Plugin"
    src="/pentaho/content/common-ui/resources/web/compressed/plugin-handler/plugin.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/PluginHandler"
    src="/pentaho/content/common-ui/resources/web/compressed/plugin-handler/pluginHandler.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_" data-requiremodule="common-ui/angular"
    src="/pentaho/content/common-ui/resources/web/compressed/angular/angular.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/angular-resource"
    src="/pentaho/content/common-ui/resources/web/compressed/angular/angular-resource.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/angular-animate"
    src="/pentaho/content/common-ui/resources/web/compressed/angular/angular-animate.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/angular-route"
    src="/pentaho/content/common-ui/resources/web/compressed/angular/angular-route.js"></script>
  <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_"
    data-requiremodule="common-ui/angular-i18n/angular-locale_pt-pt"
    src="/pentaho/content/common-ui/resources/web/compressed/angular/i18n/angular-locale_pt-pt.js"></script>
</head>

<body oncontextmenu="return false;" class="pentaho-page-background dragdrop-dropTarget dragdrop-boundary"
  style="position: relative;">

  <div ng-show="viewContainer === 'PUC'" class="ng-app-element deny-animation-change" animate="fade" id="pucWrapper"
    cellspacing="0" cellpadding="0" style="width: 100%; height: 100%; position: relative; left: 0px;">

    <div id="pucHeader" cellspacing="0" cellpadding="0">
      <div id="pucMenuBar">
        <div>
          <div tabindex="0" role="menubar" class="gwt-MenuBar gwt-MenuBar-horizontal" hidefocus="true" id="mainMenubar"
            style="outline: 0px;"><input type="text" tabindex="-1" role="presentation"
              style="opacity: 0; height: 1px; width: 1px; z-index: -1; overflow: hidden; position: absolute;">
            <table>
              <tbody>
                <tr>
                  <td class="gwt-MenuItem" id="filemenu" role="menuitem" aria-haspopup="true" onclick="setTASKDATA(1)">
                    TASKDATA1
                  </td>
                  <td class="gwt-MenuItem" id="viewmenu" role="menuitem" aria-haspopup="true" onclick="setTASKDATA(2)">
                    TASKDATA2
                  </td>
                  <td class="gwt-MenuItem" id="toolsmenu" role="menuitem" aria-haspopup="true" onclick="setTASKDATA(3)">
                    TASKDATA3
                  </td>
                  <td class="gwt-MenuItem" id="helpmenu" role="menuitem" aria-haspopup="true" onclick="setTASKDATA(4)">
                    TASKDATA4
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div id="pucPerspectives">
        <div id="mantle-perspective-switcher" class="mantle-perspective-switcher">
          <table cellspacing="0" cellpadding="0" class="custom-dropdown">
            <tbody>
              <tr>
                <td align="left" style="vertical-align: top;">
                  <div class="custom-dropdown-label"
                    style="white-space: nowrap; user-select: none; cursor: context-menu;">
                    Current taskdata:
                    <span id="current_taskdata"></span>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <div id="pucContent">
      <h1>
        Some Dummy Rules:
      </h1>
      <%
        PluginDoPentaho plugin = new PluginDoPentaho();

        //out.print("<ul>");
        
        //out.print("<li>");
        //out.print(plugin.getTASKDATA1Rules()));
        //out.print("</li>");
        
        //out.print("<li>");
        //out.print(plugin.getTASKDATA2Rules()));
        //out.print("</li>");
          
        //out.print("<li>");
        //out.print(plugin.getTASKDATA3Rules()));
        //out.print("</li>");
  
        //out.print("<li>");
        //out.print(plugin.getTASKDATA4Rules()));
        //out.print("</li>");

        //out.print("</ul>");
      %>
    </div>
  </div>

  <!-- ngView: -->
  <script>
    const setTASKDATA = (task_number) => {
      $("#current_taskdata").html(task_number);
      $.get("/pentaho/TASKDATA" + task_number, function (data, status) {
        alert("Data: " + data + "\nStatus: " + status);
      });
    };
  </script>

</body>

</html>