import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';

void main() => runApp(MyApp(window.defaultRouteName));

class MyApp extends StatelessWidget {
  String routeMessage = "";

  MyApp(String routeMessage) {
    this.routeMessage = routeMessage;
    Fluttertoast.showToast(msg: routeMessage);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(
        title: 'Flutter Demo Home Page',
        routeInfo: routeMessage,
      ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title, this.routeInfo}) : super(key: key);
  final String title;
  final String routeInfo;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  static const nativeChannel = const MethodChannel('com.example.flutter/native');
  static const flutterChannel = const MethodChannel('com.example.flutter/flutter');



  void _incrementCounter() {
    setState(() {
      _counter++;
      Fluttertoast.showToast(
          msg: "button has been clicked,count is $_counter "
          // "\t routeInfo:${widget.routeInfo}"
          );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
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
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
