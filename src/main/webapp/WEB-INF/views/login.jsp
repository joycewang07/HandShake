
<html lang="en">
<head>
    <meta name="description" content="Handshake"/>

    <link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-neptune.css"/>
    <%--<link rel="stylesheet" type="text/css" href="/extjs/resources/css/ext-all-gray.css"/>--%>
    <script type="text/javascript" src="/extjs/ext-all-dev.js"></script>
    <script type="text/javascript" src="/extjs/locale/ext-lang-en.js"></script>

    <title>Main</title>
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
         var loginFormPanel = Ext.create('Ext.form.Panel', {
               // title: templateStore.getById(203).get("label"),
                title: "login",
                id:"loginFormPanel",
                bodyPadding: 25,
                width: 400,
                url: 'login',
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
                    }, {
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
                                    window.location.href="/";
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

            var categoryStore = Ext.create('Ext.data.Store', {
             //   storeId: 'categoryStore',
                fields: ['abbr','name'],
                data : [
                    { "abbr":" CU", "name":"Company User"},
                    {"abbr":" IU", "name":"Individual User"}
                ]
            });

            var registerFormPanel = Ext.create('Ext.form.Panel', {

                title: "register",
                id:"registerFormPanel",
                frame: true,
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
                },  {
                    xtype: 'combobox',
                    fieldLabel: 'Category',
                    id: "type",
                    name: 'category',
                    store: categoryStore ,
                    valueField: 'abbr',
//                    data: {'items':[
//                    {activity:"<span>IU </span>"},
//                    {activity:"<span>CU </span>"}
//                    ]},
                    displayField: 'Category',
                    typeAhead: true,
                    queryMode: 'local',
                    emptyText: 'Select a category...'

                },, {
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
                },//pswd2

                    {
                        xtype: 'checkboxfield',
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
                            var form = this.up('form').getForm();
                            var regisform = Ext.getCmp("registerFormPanel").getForm();
                            var newUserInfo= {userID: 1, username:regisform.findField("username").getValue() ,password:regisform.findField("password").getValue(), type:"individual"}
                                Ext.Ajax.request({
                                    url: '/signup',
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


                        }//function
                    }]//item

            });



            Ext.create('Ext.window.Window', {
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

            Ext.create('Ext.window.Window', {
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
        })
    })();
</script>
</body>
</html>