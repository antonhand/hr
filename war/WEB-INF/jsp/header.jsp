<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Кадровое агентство</title>
<link href="<c:url value="/styles/GraphicArtist.css"/>" rel="stylesheet">
<link href="<c:url value="/styles/wb12_graphic_artist.css"/>" rel="stylesheet">
</head>
<body onload="ShowObject('FullScreenLayer1', 0);ShowObject('FullScreenLayer2', 0);ShowObject('FullScreenLayer3', 0);return false;">
   <header id="PageHeader1" style="position:fixed;text-align:center;left:0px;top:0px;width:100%;height:100px;z-index:7777;">
      <div id="PageHeader1_Container" style="width:1200px;position:relative;margin-left:auto;margin-right:auto;text-align:left;">
         <div id="wb_Heading4" style="position:absolute;left:14px;top:31px;width:410px;height:43px;z-index:0;">
            <h1 id="Heading4">Кадровое агентство</h1></div>
         <div id="wb_CssMenu1" style="position:absolute;left:841px;top:31px;width:336px;height:43px;text-align:right;z-index:1;">
            <ul>
               <li class="firstmain"><a href="/hr/searchEmployee.htm" target="_self">ПОИСК</a>
               </li>
               <li><a href="/hr/employees.htm" target="_self">РАБОТНИКИ</a>
               </li>
               <li><a href="/hr/companies.htm" target="_self">КОМПАНИИ</a>
               </li>
            </ul>
         </div>
      </div>
   </header>