import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';

void main() =>
    runApp(MyApp(
      title: "this is Demo",
      routeInfo: window.defaultRouteName,
    ));

class MyApp extends StatefulWidget {
  var message = "";

  MyApp({Key key, this.title, this.routeInfo}) : super(key: key);
  final String title;
  final String routeInfo;

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  int _counter = 0;
  static const nativeChannel =
  const MethodChannel('com.example.flutter/native');
  static const flutterChannel =
  const MethodChannel('com.example.flutter/flutter');

  void _incrementCounter() {
    setState(() {
      _counter++;
      Fluttertoast.showToast(msg: "button has been clicked,count is $_counter "
        // "\t routeInfo:${widget.routeInfo}"
      );
    });
  }

  void onDataChange(val) {
    setState(() {
      widget.message = val;
    });
  }

  @override
  void initState() {
    super.initState();
    Future<dynamic> handler(MethodCall call) async {
      switch (call.method) {
        case 'onActivityResult':
          onDataChange(call.arguments['message']);
          print('1234' + call.arguments['message']);
          break;
      }
    }

    flutterChannel.setMethodCallHandler(handler);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: Scaffold(
          appBar: AppBar(
            title: Text(widget.title),
          ),
          body: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text(
                  'You have pushed the button this many times:',
                ),
                Text(
                  '$_counter',
                  style: Theme
                      .of(context)
                      .textTheme
                      .headline4,
                ),
                ElevatedButton(

                    style: ButtonStyle(),
                    onPressed: () {
                      _returnLastNativePage(nativeChannel);
                    },
                    child: Text(
                      'open last native activity',
                      style: Theme
                          .of(context)
                          .textTheme
                          .headline4,
                    )),
                ElevatedButton(
                    style: null,
                    onPressed: () {
                      _openNextNativePage(nativeChannel);
                    },
                    child: Text(
                      'open next native activity',
                      style: Theme
                          .of(context)
                          .textTheme
                          .headline4,
                    ))
              ],
            ),
          ),
          floatingActionButton: FloatingActionButton(
            onPressed: _incrementCounter,
            tooltip: 'Increment',
            child: Icon(Icons.add),
          ), // This trailing comma makes auto-formatting nicer for build methods.
        ));
  }


  Future<Null> _returnLastNativePage(MethodChannel channel) async {
    Map<String, dynamic> para = {'message': '嗨，本文案来自Flutter页面，回到第一个原生页面将看到我'};
    final String result = await channel.invokeMethod('backFirstNative', para);
    print('这是在flutter中打印的' + result);
  }


  Future<Null> _openNextNativePage(MethodChannel channel) async {
    Map<String, dynamic> para = {'message': '嗨，本文案来自Flutter页面，打开第二个原生页面将看到我'};
    final String result =
    await channel.invokeMethod('openSecondNative', para);
    print('这是在flutter中打印的' + result);
  }

}
