<#include "./global.ftl" />
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="/img/favicon-expertus.png" />
    <title>Julien's Expertus challenge</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link href="/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/vendors/lightbox/css/lightbox.min.css" rel="stylesheet" />
    <link href="/css/global.min.css" rel="stylesheet" />

</head>
<body  <#if bodyOnLoad?has_content>data-onload="${bodyOnLoad}"</#if> >