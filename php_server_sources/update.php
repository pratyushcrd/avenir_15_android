<?php 

require_once('push.php');



if(isset($_POST['json'])){

$data= $_POST['json'];
$my_file = 'updates.json';
$handle = fopen($my_file, 'a') or die('Cannot open file:  '.$my_file);
$data = ",".$data;
fwrite($handle, $data);


}
else{
if(!isset($_POST['title']))
die();
if(!isset($_POST['text']))
die();
if(!isset($_POST['url']))
die();

$title = $_POST['title'];
$text = $_POST['text'];
$url = $_POST['url'];

$my_file = 'updates.json';
$handle = fopen($my_file, 'a') or die('Cannot open file:  '.$my_file);
$data = ",{\"title\":\"".$title."\",\"url\":\"".$url."\",\"text\":\"".$text."\"}";
fwrite($handle, $data);
}

?>true