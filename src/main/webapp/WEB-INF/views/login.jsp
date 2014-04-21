
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

Ext.require([
    'Ext.form.*',
    'Ext.Img',
    'Ext.tip.QuickTipManager'
]);
    (function () {
        Ext.onReady(function () {

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
                margin: '0 0 3 0'
                //top, right, bottom, left
            });



         var loginFormPanel = Ext.create('Ext.form.Panel', {
               // title: templateStore.getById(203).get("label"),
                title: "login",
                id:"loginFormPanel",
                bodyPadding: 25,
                width: 400,
                url: 'login',
                autoScroll: true,
                header: false,
                buttonAlign: 'center',
                minButtonWidth: 120,
                defaults: {
                    padding: '10 0 10 0',
                    height: 30
                },
                layout: {
                    type: 'vbox',
                    align: 'center'
                },
                items: [
                    {
                        xtype: 'textfield',
                        fieldLabel: 'User Name',
                        name: 'username'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Password',
                        name: 'password',
                        inputType: 'password'
                    },{
                        xtype      : 'fieldcontainer',
                        fieldLabel : 'Type',
                        defaultType: 'radiofield',
                        defaults: {
                            flex: 1
                        },
                        layout: 'hbox',
                        items: [{
                                boxLabel  : 'Individual',
                                name      : 'Type',
                                inputValue: 'Individual',
                                id        : 'loginIndi'
                            }, {
                                boxLabel  : 'Company',
                                name      : 'Type',
                                inputValue: 'Company',
                                id        : 'loginCmpy'
                        }]
                    },{
                        xtype: 'label',
                        itemId: 'promptLabel'
                    }
                ],
                buttons: [{
                        text:'Sign Up?',
                        listeners:{
                            click: function(){
                              Ext.getCmp("loginWindow").hide();
                                Ext.getCmp("registerWindow").show();
                                }
                            }
                    },{
                        text: 'Login',
                        handler: function () {
                            // The getForm() method returns the Ext.form.Basic instance:
                            var form = loginFormPanel.submit({
                                clientValidation: true,
                                url: '/login/check',
                                success: function (form, action) {
                                    if(Ext.getCmp("loginIndi").getValue()){
                                    window.location.href="/";
                                    }else if(Ext.getCmp("loginCmpy").getValue()){
                                        window.location.href ="/company";
                                    }else{
                                        window.location.href = "/admin";
                                    }
                                    //loginFormPanel.getComponent('promptLabel').setText('<div style="color:red">Success</div>', false);
                                },
                                failure: function (form, action) {
                                    switch (action.failureType) {
                                        case Ext.form.action.Action.CLIENT_INVALID:
                                            loginFormPanel.getComponent('promptLabel').setText('<div style="color:red">Form fields may not be submitted with invalid values</div>', false);
                                            break;
                                        case Ext.form.action.Action.CONNECT_FAILURE:
                                            loginFormPanel.getComponent('promptLabel').setText('<div style="color:red">Ajax communication failed</div>', false);
                                            break;
                                        case Ext.form.action.Action.SERVER_INVALID:
                                            //Ext.Msg.alert('Failure', promptStore.count());
                                            loginFormPanel.getComponent('promptLabel').setText((action.result.msg), false);
                                            break;
                                    }
                                }
                            })}
                    }
                ]
            });


            Ext.tip.QuickTipManager.init();


            var registerFormPanel = Ext.create('Ext.form.Panel', {

                title: "register",
                id:"registerFormPanel",
                autoScroll: true,
                frame: false,
                width: 350,
                bodyPadding: 10,
                bodyBorder: true,
                title: 'Account Registration',

                defaults: {
                    anchor: '100%'
                },
                fieldDefaults: {
                    labelWidth: 110,
                    labelAlign: 'left',
                    msgTarget: 'none',
                    invalidCls: '' //unset the invalidCls so individual fields do not get styled as invalid
                },
                items: [{
                    xtype: 'textfield',
                    name: 'username',
                    id: "username",
                    fieldLabel: 'User Name',
                    allowBlank: false,
                    minLength: 6
                }, {
                    xtype: 'textfield',
                    name: 'password1',
                    id: "password",
                    fieldLabel: 'Password',
                    inputType: 'password',
                    style: 'margin-top:15px',
                    allowBlank: false,
                    minLength: 8
                }, {
                    xtype: 'textfield',
                    name: 'password2',
                    id: "repeatUserPassword",
                    fieldLabel: 'Repeat Password',
                    inputType: 'password',
                    allowBlank: false
                    /**
                     * Custom validator implementation - checks that the value matches what was entered into
                     * the password1 field.
                     */
                    //                validator: function(value) {
                    //                  var password1 =Ext.getCmp("registerFormPanel").getForm().findField("firstpassword");
                    //                 return (value === password1.getValue()) ? true : 'Passwords do not match.'
                    //              }//pswd validator
                },  {
                    xtype      : 'fieldcontainer',
                    fieldLabel : 'Type',
                    defaultType: 'radiofield',
                    defaults: {
                        flex: 1
                    },
                    layout: 'hbox',
                    items: [
                        {
                            boxLabel  : 'Individual',
                            name      : 'Type',
                            inputValue: 'Individual',
                            id        : 'individual'
                        }, {
                            boxLabel  : 'Company',
                            name      : 'Type',
                            inputValue: 'Company',
                            id        : 'company',
                            handler: function(){
                                //var tru = this.getValue();
                                if(this.getValue()){
                                  //  var it = Ext.getCmp("companyname");
                                    Ext.getCmp("companyname").show();
                                    Ext.getCmp("address").show();
                                    Ext.getCmp("tel").show();
                                }else{}
                            }
                        }
                    ]
                }, {
                    xtype: 'textfield',
                    name: 'company name',
                    id: 'companyname',
                    hidden: true,
                    fieldLabel: 'Company Name',
                    // allowBlank: false,
                    minLength: 6
                },{
                    xtype: 'textfield',
                    name: 'address',
                    id: "address",
                    hidden: true,
                    fieldLabel: 'Address',
                    //  allowBlank: false,
                    minLength: 6
                },{
                    xtype: 'textfield',
                    name: 'Tele',
                    id: "tel",
                    hidden: true,
                    fieldLabel: 'Telephone',
                    //allowBlank: false,
                    minLength: 6
                },
                      { xtype: 'checkboxfield',
                        name: 'acceptTerms',
                        fieldLabel: 'Terms of Use',
                        hideLabel: true,
                        margin: '15 0 0 0',
                        boxLabel: 'I have read and accept the <a href="#" class="terms">Terms of Use</a>.'
                        // Detelet:  Listener to open the Terms of Use page link in a modal window
                        // Custom validation logic - requires the checkbox to be checked
//                        getErrors: function() {
//                            return this.getValue() ? [] : ['You must accept the Terms of Use']
//                        }
                    }],//item

                    buttons: [{
                        xtype: 'button',
                      //  formBind: true,
                    //    disabled: true,
                        text: 'Submit Registration',
                       // width: 140,
                        handler: function() {
                        // var   individual = Ext.getCmp("individual").getValue();
                            var form = this.up('form').getForm();
                            var regisform = Ext.getCmp("registerFormPanel").getForm();
                            if(Ext.getCmp("individual").getValue()){

                            var newUserInfo= {userID: 1, username:regisform.findField("username").getValue(),password:regisform.findField("password").getValue(), type:"individual"};
                                Ext.Ajax.request({
                                    url: '/signup/individual',
                                    params: Ext.encode(newUserInfo),
                                    headers: {'Accept': 'application/json', 'Content-Type':'application/json'},
                                    method:'POST',

                                    success: function(response, opts) {
                                        Ext.getCmp("loginWindow").show();
                                        Ext.getCmp("registerWindow").hide();
                                    },

                                    failure: function(response, opts) {
                                        console.log('server-side failure with status code ' + response.status);
                                    }
                                });
                        }else{
                                var newUserInfo= {userID: 1, username:regisform.findField("username").getValue(),password:regisform.findField("password").getValue(), companyName: regisform.findField("companyname").getValue(), address:regisform.findField("address").getValue(), tel:regisform.findField("tel").getValue()};
                                Ext.Ajax.request({
                                    url: '/signup/company',
                                    params: Ext.encode(newUserInfo),
                                    headers: {'Accept': 'application/json', 'Content-Type':'application/json'},
                                    method:'POST',

                                    success: function(response, opts) {
                                        Ext.getCmp("loginWindow").show();
                                        Ext.getCmp("registerWindow").hide();
                                    },

                                    failure: function(response, opts) {
                                        console.log('server-side failure with status code ' + response.status);
                                    }
                                });
                            }
                    }//function
                    },{
                        xtype:'button',
                        text:'Cancel',
                        handler: function(){
                            Ext.getCmp("registerWindow").close();
                            Ext.getCmp("loginWindow").show();
                        }
                    }]//item

            });

            var loginWidow = Ext.create('Ext.window.Window', {
                id: "loginWindow",
                title: 'Login',
                titleAlign: 'center',
                x: window.screen.width * 0.618,
                y: window.screen.height * 0.3,
                height: 270,
                width: 420,
                resizable: false,
                closable: false,
                layout: 'fit',
                items: [loginFormPanel]
            }).show();

            var registerWindow = Ext.create('Ext.window.Window', {
                id: "registerWindow",
                title: 'Registration',
                titleAlign: 'center',
                x: window.screen.width * 0.15,
                y: window.screen.height * 0.1,
                height: 300,
                width: 420,
                resizable: false,
                closable: false,
                layout: 'fit',
                items: [registerFormPanel]
            });

            var mainViewPort = Ext.create('Ext.container.Viewport', {
                layout: 'border',
                style:'background-image:url(images/hsbgd.jpg)',
                items: [topBar,  loginWidow],
                renderTo: Ext.getBody()
            });
        })
    })();
</script>
</body>
</html>