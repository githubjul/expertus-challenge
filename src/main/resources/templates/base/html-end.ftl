<script charset="UTF-8" src="/vendors/jquery/jquery.min.js"></script>
<script charset="UTF-8" src="/vendors/bootstrap/js/bootstrap.min.js"></script>
<script charset="UTF-8" src="/vendors/flip/flip.min.js"></script>
<script charset="UTF-8" src="/vendors/lightbox/js/lightbox.min.js"></script>

<script charset="UTF-8" src="/js/global.min.js"></script>



<#if googleAnalyticsId?has_content>
    <script async src="https://www.googletagmanager.com/gtag/js?id=${googleAnalyticsId}"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());

        gtag('config', '${googleAnalyticsId}');
    </script>
</#if>

</body>
</html>