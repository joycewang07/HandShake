<!--
Created by IntelliJ IDEA.
UserEntity: joycewang
Date: 1-3-2013
Time: 10:13
-->
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

            var adminToolBar = Ext.create('Ext.toolbar.Toolbar', {
                width: '40%',
                region: 'north',
                layout: {
                    type: 'hbox',
                    align: 'middle'
                    // pack: 'end'
                },
                items: [{
                        xtype: 'button',
                        text: 'Generate Report',
                        width: 150,
                        height: 30,
                        handler: function () {
                            window.location.href = "/report";
                        //generate PDF
//                            var arrayData = new Array();
//                            var grid = Ext.getCmp("mainPanel").getStore().data.items;
//                            var jd= Ext.encode(grid);
//                            var jsonList = new Array();
//                            for (var i = 0; i < grid.length; i++) {
//                                arrayData.push({activityName: grid[i].data.activityName, companyName})
//                                arrayData.push(grid[i].data);
//                                var jdf = Ext.encode(arrayData[0]);
//                                jsonList[i] = jdf;
//                            }
//                            var result = Ext.encode(jsonList);
//                            Ext.Ajax.request({
//                            url: '/report',
//                            headers: {'Accept': 'application/json', 'Content-Type':'application/json'},
//                            params: result,
//                            method: 'POST',
//                            success: function(response, opts) {},
//                            failure: function(response, opts) {
//                                console.log('server-side failure with status code ' + response.status);
//                            }
//                        });

                        }}]
            });


            Ext.define('generalInfoModel', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'companyName', type: 'string'},
                    {name: 'activityName', type: 'string'},
                    {name: 'userAmount', type: 'int'}
                 //   {name: 'companyEntity', type: 'int'},
                 //   {name: 'industry', type: 'string'}
                ]
            });

            var generalInfoStore = Ext.create('Ext.data.Store', {
                storeId: 'generalInfoStore',
                model: 'generalInfoModel',

                proxy: {
                    method: "GET",
                    url: '/admin/displayGeneralInfo',
                    type: 'ajax',
                    limitParam: undefined,
                    pageParam: undefined,
                    startParam: undefined,
                    reader: {
                        type: 'json'
                        // root: 'items'
                    }
                },
                autoLoad: true
            });

            var mainPanel = Ext.create('Ext.grid.Panel', {
                id: "mainPanel",
                title: 'General Info',
                store: generalInfoStore,
                columns: [{
                        text: 'Company',
                        dataIndex: 'companyName',
                        flex: 1
                    },{
                        text: 'Activity Name',
                        dataIndex: 'activityName',
                        flex: 2
                    },{
                        text: 'Attendee Number',
                        dataIndex: 'userAmount',
                        flex: 1
                    }],
                region: 'center',
                collapsible: true,
                //width: 400,
                //height: 400,
                split: true,
                margin: '10 10 10 10'

               // listeners: {itemclick: onActivityClick}
            });


        //Control Panel
            var controlPanel = Ext.create('Ext.panel.Panel', {
                title: 'Control',
                id: "rightPanel",
                layout: 'fit',
                width: '30%',
                region: 'east',
                margin: '10 10 10 10'
                //style:'background-image:url(images/controlPpic.jpg)'
                //items:[backgroundImage]
            });

            var backgroundImage = Ext.create('Ext.Img',{
                src: '/images/controlPpic.jpg',
                renderTo: Ext.get("rightPanel"),
                region: 'east',
                width: 300
            })

            var mainViewPort = Ext.create('Ext.container.Viewport', {
                layout: 'border',
                items: [topBar, adminToolBar, mainPanel, backgroundImage],
                renderTo: Ext.getBody()
            });

        }

        Ext.onReady(function () {
            initComponent();

        });


    })();
</script>

</body>
</body>
</html>