<?php
	
// Push The notification with parameters
require_once('PushBots.class.php');
$pb = new PushBots();
// Application ID
$appID = '54d87db91d0ab13c058b45dc';
// Application Secret
$appSecret = '8de769de4171e601071b026319b9cc2f';
$pb->App($appID, $appSecret);
 
// Notification Settings
$pb->Alert("New Notifications received!");
$pb->Platform(array(0,1));

$pb->Push();
?>