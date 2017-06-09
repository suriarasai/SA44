<?xml version="1.0" encoding="UTF-8" ?>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Sitemesh Demo</title>
</head>
<body>
	<div>
		<h1>Some Sensible Header</h1>
		<p>
			<b>All You Navigation</b>
		</p>
		<hr />
	</div>
	<div>
	<decorator:body />
	</div>
	<div>
	<small>All your company's insecure caution and propertied clauses
		footer</small>
		
	</div>
</body>
</html>