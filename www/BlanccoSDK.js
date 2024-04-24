var PLUGIN_NAME = "BlanccoSDKPlugin";
// @ts-ignore
var exec = require("cordova/exec");

exports.activate =
  /**
   * Activates the Blancco SDK
   *
   * @param {string} key The Blancco SDK key
   * @returns {Promise<void>} Callback when operation is completed
   *
   * @example
   * cordova.plugins.BlanccoSDK(key).activate().then(() => {
   *    console.log("Blancco SDK activated");
   * }).catch((error) => {
   *   console.error("Error activating Blancco SDK", error);
   * });
   */
  function (key) {
    return new Promise(function (resolve, reject) {
      exec(resolve, reject, PLUGIN_NAME, "activate", [key]);
    });
  };

exports.runSequence =
  /**
   * Runs a Blancco SDK sequence
   *
   * @param {Array<number>} sequence The sequence to run
   * @returns {Promise<void>} Callback when operation is completed
   *
   * @example
   * cordova.plugins.BlanccoSDK.runSequence([1, 2, 3]).then(() => {
   *   console.log("Blancco SDK sequence run");
   * }).catch((error) => {
   *  console.error("Error running Blancco SDK sequence", error);
   * });
   */
  function (sequence) {
    return new Promise(function (resolve, reject) {
      exec(resolve, reject, PLUGIN_NAME, "runSequence", [sequence]);
    });
  };
