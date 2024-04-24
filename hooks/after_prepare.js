#!/usr/bin/env node
const fs = require('fs');
const xml2js = require('xml2js');

const manifestPath = 'platforms/android/app/src/main/AndroidManifest.xml';

fs.readFile(manifestPath, 'utf8', (err, data) => {
  if (err) {
    console.error('Error reading AndroidManifest.xml:', err);
    return;
  }

  xml2js.parseString(data, (parseErr, result) => {
    if (parseErr) {
      console.error('Error parsing AndroidManifest.xml:', parseErr);
      return;
    }

    const permissions = new Set();
    const filteredUsesPermissions = [];

    result.manifest['uses-permission'].forEach(permission => {
        const permissionName = permission['$']['android:name'];
        if (!permissions.has(permissionName)) {
            permissions.add(permissionName);
            filteredUsesPermissions.push(permission);
        }
    });

    result.manifest['uses-permission'] = filteredUsesPermissions;

    const builder = new xml2js.Builder();
    const modifiedXml = builder.buildObject(result);

    fs.writeFile(manifestPath, modifiedXml, 'utf8', writeErr => {
      if (writeErr) {
        console.error('Error writing modified AndroidManifest.xml:', writeErr);
        return;
      }
      console.log('AndroidManifest.xml updated successfully.');
    });
  });
});
