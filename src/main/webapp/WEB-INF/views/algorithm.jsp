<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<html lang="en">
<head>
    <meta name="description" content="Handshake"/>

    <%--<link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-neptune.css"/>--%>
    <link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-gray.css"/>
    <script type="text/javascript" src="/extjs/ext-all-dev.js"></script>
    <script type="text/javascript" src="/extjs/locale/ext-lang-en.js"></script>

    <title>Handshake</title>
</head>

<body>
<script type="text/javascript">
(function () {
    function initComponent() {
        var topLogoPanel = Ext.create('Ext.panel.Panel', {
            id: "topLogoPanel",
            bodyStyle: {
                background: '#FFFFFF'
            },
            border: false,
            html: '<h1><div style="color:#ffffff; margin-top: 10px; height:25px; line-height:40px;text-indent:60px"><img src="/images/HandShakelogo.jpg" alt="Error" width="320" height="30"></div></h1>',
            width: window.screen.width * 0.9
        });

        var topInfoPanel = Ext.create('Ext.panel.Panel', {
            id: "topInfoPanel",
            bodyStyle: {
                background: '#FFFFFF'
            },
            border: false,
            html: '<div style="color:#4C4C4C;height:40px;line-height:20px"><br/>Joyce Wang<br/>Ver: 0.1</div>',
            // html: '<div style="color:#4C4C4C;height:40px;"><b style="font-size: 10px;"><i>Welcome back Paul!</i></b><img style="float: left;" src="/images/paulLogin.jpg" alt="Error" width="35" height="35"></div><img style="float: right; margin-bottom: 1px" src="/images/logout.jpg" alt="Error" width="50" height="25">',

            width: window.screen.width * 0.1
        });

        var topBar = Ext.create('Ext.panel.Panel', {
            id: "topBar",
            region: 'north',
            height: '30px',
            border: false,
            bodyStyle: {
                background: '#FFFFFF'
            },
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [topLogoPanel, topInfoPanel],
            margin: '0 0 3 0'  //top, right, bottom, left
        });



        var cardPanel = Ext.create('Ext.panel.Panel', {
            title: 'Card Detail',
            //header: false,
            layout: 'vbox',
            html: '<p> Card Detail will be displayed </p>',
            region: 'east',
            width: '30%',
            height: 300,
            collapsible: true,
            split: true,
            margin: '0 0 5 0',
            dockedItems: [cardToolBar, cardDetailForm]
        });

        var rightPanel = Ext.create('Ext.panel.Panel', {
            id: "rightPanel",
            layout: 'border',
            region: 'center',
            items: [cardPanel]
        });

        var mainViewPort = Ext.create('Ext.container.Viewport', {
            layout: 'border',
            items: [topBar, activityListPanel, rightPanel],
            renderTo: Ext.getBody()
        });











        }


    Ext.onReady(function () {
        initComponent();
    });
})();
</script>

</body>
</html>