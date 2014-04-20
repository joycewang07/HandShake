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

        Ext.define('ActivityListModel', {
            extend: 'Ext.data.Model',
            fields: [
                {name: 'activityId', type: 'int'},
                {name: 'date', type: 'string'},
                {name: 'name', type: 'string'},
                {name: 'companyEntity', type: 'int'},
                {name: 'industry', type: 'string'}

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
                url: '/company/activity/list',
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

        var activityListPanel = Ext.create('Ext.grid.Panel', {
            id: "activityListPanel",
            title: 'Event',
            store: activityListStore,
            columns: [
                {
                    text: 'Date',
                    dataIndex: 'date',
                    flex: 1
                },
                {
                    text: 'Activity Name',
                    dataIndex: 'name',
                    flex: 2
                }
            ],
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
                {name: 'name', type: 'string'},
                {name: 'cardId', type: 'int'},
                {name: 'image', type: 'string'},
                {name: 'html', type: 'string'},
                {name: 'company', type: 'string'},
                {name: 'email', type: 'string'},
                {name: 'title', type: 'string'},
                {name: 'success', type: 'boolean'}
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
                url: '/company/cardList',
                type: 'ajax',
                limitParam: undefined,
                pageParam: undefined,
                startParam: undefined,
                reader: {
                    type: 'json'
                    // root: 'items'
                }
            },
            autoLoad: false
        });
    //south
        var cardListToolBar = Ext.create('Ext.toolbar.Toolbar', {
            width: '40%',
            region: 'south',
            layout: {
                type: 'hbox',
                align: 'middle'
                // pack: 'end'
            },
            items: [
                {
                    xtype: 'button',
                    text: 'Create Activity',
                    width: 150,
                    height: 30,
                    handler: function () {
                        //Ext.Msg.alert('Popup window', 'Display and edit your button');
//
//                        //ajax myCardDisplay
//                        Ext.Ajax.request({
//                            url: '/mycard/display',
//
//                            success: function(response, opts) {
//                                var myCardInformation = Ext.decode(response.responseText);
//                                var myCardForm = Ext.getCmp("myCardForm").getForm();
//                                myCardForm.setValues(myCardInformation);
//                                //console.dir(obj);
//                            },
//                            failure: function(response, opts) {
//                                console.log('server-side failure with status code ' + response.status);
//                            }
//                        });
                        myCardWindow.show();
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
            columns: [
                { text: 'Company',  dataIndex: 'name' },
                { text: 'Activity', dataIndex: '', flex: 1 },
                { text: 'Attendee', dataIndex: 'phone' }
            ],
            region: 'center',
            split: true,
            margin: '5 5 5 5',
            dockedItems: [cardListToolBar],
            listeners: {itemclick: onCardClick}
        });

        Ext.create('Ext.data.Store', {
            storeId:'simpsonsStore',
            fields:['name', 'email', 'phone'],
            data:{'items':[
                { 'name': 'Lisa',  "email":"lisa@simpsons.com",  "phone":"555-111-1224"  },
                { 'name': 'Bart',  "email":"bart@simpsons.com",  "phone":"555-222-1234" },
                { 'name': 'Homer', "email":"home@simpsons.com",  "phone":"555-222-1244"  },
                { 'name': 'Marge', "email":"marge@simpsons.com", "phone":"555-222-1254"  }
            ]},
            proxy: {
                type: 'memory',
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }
        });

        Ext.create('Ext.grid.Panel', {
            title: 'Simpsons',
            store: Ext.data.StoreManager.lookup('simpsonsStore'),
            columns: [
                { text: 'Name',  dataIndex: 'name' },
                { text: 'Email', dataIndex: 'email', flex: 1 },
                { text: 'Phone', dataIndex: 'phone' }
            ],
            height: 200,
            width: 400,
            renderTo: Ext.getBody()
        });



        var cardToolBar = Ext.create('Ext.toolbar.Toolbar', {
            width: '100%',
            region: 'north',
            layout: {
                type: 'vbox',
                align: 'middle'
            }
        });

        //card form, Card Detail
        var cardDetailForm = Ext.create('Ext.form.Panel', {
            id: 'cardDetailForm',
            //url: 'save-form',
            frame: false,
            border: false,
            //  title: 'User\'s Card',
            header: false,
            bodyPadding: '15 35 0 35',
            collapsible: true,
            width: 300,
            layout: 'vbox',
            fieldDefaults: {
                msgTarget: 'side',
                labelWidth: 80
            },
            defaults: {
                //anchor: '100%'
                width: 300
            },
            items: [{
                xtype: 'fieldset',
                title: 'User Information',
                defaultType: 'textfield',
                //layout: 'anchor',
                defaults: {
                    anchor: '100%'
                },
                items :[{
                    fieldLabel: 'Name',
                    name: 'name',
                    allowBlank:false,
                    readOnly: true
                }, {
                    fieldLabel: 'Company',
                    name: 'company',
                    allowBlank:false,
                    readOnly: true
                },{
                    fieldLabel: 'Title',
                    name: 'title',
                    allowBlank:false,
                    readOnly: true
                },{
                    fieldLabel: 'Email',
                    name: 'email',
                    allowBlank:false,
                    readOnly: true
                }]
            }, {
                xtype: 'fieldset',
                title: 'Note',
                collapsible: true,
                collapsed: false,
                defaultType: 'textarea',
              //x     layout: 'anchor',
                defaults: {
                    width: 500
                },
                items: [{
                    fieldLabel: 'Note',
                    name: 'note',
                    id: "noteTextArea"
                    //  storeId: 'commentStore'
                }]
            }],

            buttons: [{
                text: 'Save',
                handler: function () {
                    if(this.up('form').getForm().isValid()){
                        //ajax
                        var selectedUserId = Ext.getCmp("cardListPanel").getSelectionModel().getSelection()[0].get("userID");
                        var commentEntity = {user2Id:selectedUserId, comment: this.up('form').getForm().findField("noteTextArea").getValue()};
                        Ext.Ajax.request({
                            url: '/comment/update',
                            headers: {'Accept': 'application/json', 'Content-Type':'application/json'},
                            params: Ext.encode(commentEntity),
                            method: 'POST',
                            success: function(response, opts) {
//                                    //encode: js-->json
//                                    var myCardInformation = Ext.decode(response.responseText);
//                                    var myCardForm = Ext.getCmp("myCardForm").getForm();
//                                    myCardForm.setValues(myCardInformation);
                            },
                            failure: function(response, opts) {
                                console.log('server-side failure with status code ' + response.status);
                            }
                        });
                    }
                }
            }]
        });


        var cardPanel = Ext.create('Ext.panel.Panel', {
            title: 'Card Detail',
            //header: false,
            layout: 'vbox',
            //html: '<p> Card Detail will be displayed </p>',
            region: 'south',
            width: '30%',
            height: 300,
            collapsible: true,
            split: true,
            margin: '5 5 5 5',
           // autoRender: false,
            dockedItems: [cardToolBar, cardDetailForm]
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
        //this is the My card windows
        var myCardForm = Ext.create('Ext.form.Panel', {
            id: 'createCardForm',
            collapsible: false,
            frame: true,
            title: 'Create Activity',
            bodyPadding: '5 5 0',
            //width: 600,
            fieldDefaults: {
                labelAlign: 'top',
                msgTarget: 'side'
            },
            items: [{
                xtype: 'container',
                anchor: '100%',
                layout: 'vbox',
                items: [
                    {
                            xtype: 'hiddenfield',
                            name: 'activityId'
                        },
                    {
                            xtype: 'hiddenfield',
                            name: 'companyEntity',
                            id:'companyUserId'
                        },
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Name',
                            id:'activityName',
                            name: 'name',
                            anchor:'95%'
                        },{
                            xtype:'textfield',
                            fieldLabel: 'Industry',
                            name: 'industry',
                            id: 'industry',
                            anchor:'100%'
                        },{
                             xtype: 'textfield',
                             fieldLabel: 'Date',
                             allowBlank: false,
                             name: 'date',
                             id:'eventDate',
                             anchor: '100%'
                            }]
            }],
            buttons: [{
                text: 'Create',
                handler: function () {
                 if(this.up('form').getForm().isValid()){
                      var createActivityForm = this.up('form').getForm();
                    //  var cid = createActivityForm.findField("companyUserId").getValue();
          var newActivityEntity = {activityId: 2,companyEntity:9, name: createActivityForm.findField("activityName").getValue(),
                                date: createActivityForm.findField("date").getValue(),industry: createActivityForm.findField("industry").getValue()};

                        Ext.Ajax.request({
                            url: '/company/createActivity',
                            params: Ext.encode(newActivityEntity),
                            headers: {'Accept': 'application/json', 'Content-Type':'application/json'},
                            method:'POST',

                            success: function(response, opts) {
                                Ext.getStore("activityListStore").load();
                                Ext.getCmp("myCardWindow").close();
                            },

                            failure: function(response, opts) {
                                console.log('server-side failure with status code ' + response.status);
                            }
                        });}
                   }
                },{
                    text: 'Cancel',
                    handler: function () {
                        var myCardWindow = Ext.getCmp("myCardWindow");
                        myCardWindow.hide();
                        // this.up('form').getForm().reset();
                        }
                    }]
         //  }]
        });



        var myCardWindow= Ext.create('Ext.window.Window', {
            id: "myCardWindow",
            title: "My Card",
            titleAlign: 'center',
            x: 200,
            y: 200,
            //x: window.screen.width * 0.5,
            //y: window.screen.width * 0.5,
            height: 300,
            width: 420,
            resizable: false,
            closable: false,
            layout: 'fit',
            items: [myCardForm],
            renderTo: Ext.getBody()
        })


        function onActivityClick(component, record, item, index, e, eOpts) {
            cardListStore.getProxy().extraParams = {activityId: record.get('activityId')};
            cardListStore.load();
        }

        function onCardClick(component, record, item, index, e, eOpts) {
            cardDetailForm.loadRecord(record);
            Ext.Ajax.request({
                url: '/comment/display',
                params: {user2Id: record.get('userID')},
                method:'GET',
                success: function(response, opts) {
                    var commentInfo = Ext.decode(response.responseText);
                    Ext.getCmp("cardDetailForm").getForm().findField("noteTextArea").setValue(commentInfo.comment);

                    //console.dir(obj);
                },
                failure: function(response, opts) {
                    console.log('server-side failure with status code ' + response.status);
                }
            });



        }
    }

    Ext.onReady(function () {
        initComponent();
    });
})();
</script>
</body>
</html>