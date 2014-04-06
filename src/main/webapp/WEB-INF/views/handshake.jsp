<!--
Created by IntelliJ IDEA.
UserEntity: zjy
Date: 1-3-2013
Time: 10:13
-->
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<html lang="en">
<head>
    <meta name="description" content="Handshake"/>
    <meta name="author" content="zjy"/>
    <meta name="copyright" content="zjy"/>
    <%--<link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-neptune.css"/>--%>
    <link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-gray.css"/>
    <script type="text/javascript" src="/extjs/ext-all-dev.js"></script>
    <script type="text/javascript" src="/extjs/locale/ext-lang-en.js"></script>

    <title>Handshake</title>
</head>

<body>
<script type="text/javascript">
    (function () {
        function initComponent () {
            var topLogoPanel = Ext.create('Ext.panel.Panel', {
                id: "topLogoPanel",
                bodyStyle: {
                    background: '#05C705'
                },
                border: false,
                html: '<h1><div style="color:#ffffff; margin-top: 10px; height:25px; line-height:40px;text-indent:60px"><img src="/images/HandShakelogo.jpg" alt="Error" width="320" height="30"></div></h1>',
                width: window.screen.width * 0.9
            });

            var topInfoPanel = Ext.create('Ext.panel.Panel', {
                id: "topInfoPanel",
                bodyStyle: {
                    background: '#05C705'
                },
                border: false,
                html: '<div style="color:#ffffff;height:40px;line-height:20px"><br/>Joyce Wang<br/>Ver: 0.1</div>',
                width: window.screen.width * 0.1
            });

            var topBar = Ext.create('Ext.panel.Panel', {
                id: "topBar",
                region: 'north',
                height: '30px',
                border: false,
                bodyStyle: {
                    background: '#05C705'
                },
                layout: {
                    type: 'hbox',
                    align: 'stretch'
                },
                items: [topLogoPanel, topInfoPanel],
                margin: '0 0 3 0'  //top, right, bottom, left
            });

            Ext.define('ActivityListModel', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'activityId', type: 'int'},
                    {name: 'date', type: 'string'},
                    {name: 'name', type: 'string'}
                ]
            });

            var activityListStore = Ext.create('Ext.data.Store', {
                storeId: 'activityListStore',
                model: 'ActivityListModel',
//                data: {'items':[
//                    {date: '03/28/2014', activity:"<span>Activity Name 1 </span></br>Activity Detail</span>"},
//                    {date: '03/28/2014', activity:"<span>Activity Name 1 </span></br>Activity Detail</span>"},
//                    {date: '03/28/2014', activity:"<span>Activity Name 1 </span></br>Activity Detail</span>"},
//                    {date: '03/28/2014', activity:"<span>Activity Name 1 </span></br>Activity Detail</span>"}
//                ]},
                proxy: {
                    method: "GET",
                    url:'/activity/list',
                    type: 'ajax',
                    limitParam : undefined,
                    pageParam : undefined,
                    startParam : undefined,
                    reader: {
                        type: 'json'
                       // root: 'items'
                    }
                },
                autoLoad:true
            });

            var activityListPanel = Ext.create('Ext.grid.Panel', {
                id: "activityListPanel",
                title: 'Event',
                store: activityListStore,
                columns: [{
                    text: 'Date',
                    dataIndex: 'date',
                    flex: 1
                }, {
                    text: 'Activity Name',
                    dataIndex: 'name',
                    flex: 2
                }],
                region: 'west',
                collapsible: true,
                width: '20%',
                split: true,
                margin: '0 0 5 0',
                listeners: {itemclick: onActivityClick}
            });

            Ext.define('CardListModel', {
                extend: 'Ext.data.Model',
                fields: [
                    {name: 'userID', type: 'int'},
                    {name: 'type', type: 'string'},
                    {name: 'card', type: 'string'},
                    {name: 'html', type: 'string'}
                ]
            });

            var cardListStore = Ext.create('Ext.data.Store', {
                storeId: 'cardListStore',
                model: 'CardListModel',
//                data: {'items':[
//                    {card:"<span>Card 1 </span></br>Card Detail 1</br>Card Detail 2</span>"},
//                    {card:"<span>Card 2 </span></br>Card Detail 1</br>Card Detail 2</span>"},
//                    {card:"<span>Card 3 </span></br>Card Detail 1</br>Card Detail 2</span>"},
//                    {card:"<span>Card 4 </span></br>Card Detail 1</br>Card Detail 2</span>"}
//                ]},
                proxy: {
                    type: 'ajax',
                    method: "GET",
                    url:'/cardList',
                    type: 'ajax',
                    limitParam : undefined,
                    pageParam : undefined,
                    startParam : undefined,
                    reader: {
                        type: 'json'
                       // root: 'items'
                    }
                },
                autoLoad: false
            });

            var cardListToolBar = Ext.create('Ext.toolbar.Toolbar', {
                width: '40%',
                region: 'east',
                layout: {
                    type: 'hbox',
                    align:'middle'
                   // pack: 'end'
                },
                items: [
                    {
                        xtype: 'button',
                        text: 'My Card',
                        width: 150,
                        height: 30,
                        handler: function() {
                            Ext.Msg.alert('Popup window', 'Display and edit your button');
                        }
                    },
                    {
                        xtype: 'tbfill'//, width: 600
                    },
                    {
                        xtype: 'textfield',
                        emptyText: 'Search Card',
                        height: 30,
                        width: 300
                    },
                    {
                        xtype: 'button',
                        text: 'Search',
                        height: 30,
                        width: 100
                    }
                ]
            });

            var cardListPanel = Ext.create('Ext.grid.Panel', {
                id: "cardListPanel",
                title: 'Card List',
                //header: false,
                store: cardListStore,
                columns: [{
                    text: 'Card',
                    dataIndex: 'html',
                    flex: 1
                }],
                region: 'center',
                split: true,
                margin: '0 0 5 0',
                dockedItems: [cardListToolBar],
                listeners: {itemclick: onActivityClick}
            });

            var cardToolBar = Ext.create('Ext.toolbar.Toolbar', {
                width: '100%',
                region: 'north',
                layout: {
                    type: 'hbox',
                    align:'middle'
                },
                items: [
                    {
                        xtype: 'button',
                        text: 'Save changes',
                        width: 150,
                        height: 30,
                        handler: function() {
                            Ext.Msg.alert('Popup window', 'Save changes');
                        }
                    }
                ]
            });

            var cardPanel = Ext.create('Ext.panel.Panel', {
                title: 'Card Detail',
                //header: false,
                html: '<p> Card Detail will be displayed </p>',
                region: 'east',
                width:'30%',
                height: 300,
                collapsible: true,
                split: true,
                margin: '0 0 5 0',
                dockedItems: [cardToolBar]
            });

            var rightPanel = Ext.create('Ext.panel.Panel', {
                id: "rightPanel",
                layout: 'border',
                region: 'center',
                items: [cardListPanel, cardPanel]
            });

            var mainViewPort = Ext.create('Ext.container.Viewport', {
                layout: 'border',
                items: [topBar, activityListPanel, rightPanel],
                renderTo: Ext.getBody()
            });

            function onActivityClick (component, record, item, index, e, eOpts) {
                cardListStore.getProxy().extraParams = {activityId: record.get('activityId')};
                cardListStore.load();
            }
        }

        Ext.onReady(function () {
            initComponent();
        });
    })();
</script>
</body>
</html>