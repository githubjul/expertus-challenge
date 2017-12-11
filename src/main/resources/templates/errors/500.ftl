<#include "../base/html-start.ftl" />
<#include "../base/body-start.ftl" />
<div class="container text-center">
	<h1><@spring.message "generic.500.title" /></h1>
	<p>
		<@spring.message "generic.500.error" />
	</p>
	<div class='text-center'>
		<a href='/' class='btn btn-default'><@spring.message "generic.returnhome" /></a>
	</div>
</div>
<#include "../base/body-end.ftl" />
<#include "../base/html-end.ftl" />