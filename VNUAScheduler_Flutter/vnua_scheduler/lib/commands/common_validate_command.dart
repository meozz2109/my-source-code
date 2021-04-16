import 'dart:async';
import 'dart:io';

import 'package:vnua_scheduler/commands/base_command.dart';

class ComValidateCommand extends BaseCommand {

  Future<String> validateUserRole(String userInputCode) async {
    // Await some service call
    // bool loginSuccess = await userService.login(user, pass);

    // Run a 2nd command if service call was successful
    // if (loginSuccess) {
    //   await RefreshPostsCommand().run(user);
    // }
    // Update appModel with current user. Any views bound to this will rebuild
    // appModel.currentUser = loginSuccess? user : null;

    String role = "";

    // Return the result to whoever called us, in case they care
    return role;
  }

  Future<bool> checkUpdateServerAndCaptcha(String userInputCode) async {
    // Await some service call
    // bool loginSuccess = await userService.login(user, pass);

    // Run a 2nd command if service call was successful
    // if (loginSuccess) {
    //   await RefreshPostsCommand().run(user);
    // }
    // Update appModel with current user. Any views bound to this will rebuild
    // appModel.currentUser = loginSuccess? user : null;

    bool isUpdateServer = false,
         isRegisteredDateAlWiCaptcha = false;

    try {
      var result = await userService.handleNormalSchedule(userInputCode);

    // rest of the code

  } on TimeoutException catch (_) {
  // A timeout occurred.
  } on SocketException catch (_) {
  // Other exception
  }
    // Return the result to whoever called us, in case they care
    return isUpdateServer && isRegisteredDateAlWiCaptcha;
  }

  Future<bool> checkInternetConnection(String userInputCode) async {
    // Await some service call
    // bool loginSuccess = await userService.login(user, pass);

    // Run a 2nd command if service call was successful
    // if (loginSuccess) {
    //   await RefreshPostsCommand().run(user);
    // }
    // Update appModel with current user. Any views bound to this will rebuild
    // appModel.currentUser = loginSuccess? user : null;

    bool noConnection = false;

    // Return the result to whoever called us, in case they care
    return noConnection;
  }

}