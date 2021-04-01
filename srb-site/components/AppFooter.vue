<template>
  <!--网站底部-->
  <div id="footer" class="ft">
    <div class="ft-inner clearfix">
      <div class="ft-helper clearfix">
        <dl>
          <dt>关于我们</dt>
          <dd>
            <NuxtLink to="/about/profile">公司简介</NuxtLink
            ><NuxtLink to="/about/leader">管理团队</NuxtLink
            ><NuxtLink to="/about/report">网站公告</NuxtLink>
          </dd>
        </dl>
        <dl>
          <dt>相关业务</dt>
          <dd>
            <NuxtLink to="/invest">我要投资</NuxtLink
            ><NuxtLink to="/borrow">我要借款</NuxtLink>
          </dd>
        </dl>
        <dl>
          <dt>帮助中心</dt>
          <dd>
            <NuxtLink to="/help">新手入门</NuxtLink
            ><NuxtLink to="/user">我的账户</NuxtLink>
          </dd>
        </dl>
        <dl>
          <dt>联系我们</dt>
          <dd><NuxtLink to="/about">联系我们</NuxtLink></dd>
        </dl>
      </div>
      <div class="ft-service">
        <dl>
          <dd>
            <p>
              <strong>000-000-0000</strong><br />
              工作日 9:00-22:00<br />
              官方交流群:<em>12345678</em><br />
              工作日 9:00-22:00 / 周六 9:00-18:00<br />
            </p>
            <div class="ft-serv-handle clearfix">
              <a
                class="icon-hdSprite icon-ft-sina a-move a-moveHover"
                title="尚融宝新浪微博"
                target="_blank"
                href="#"
              ></a>
              <a
                class="icon-hdSprite icon-ft-qqweibo a-move a-moveHover"
                title="尚融宝腾讯微博"
                target="_blank"
                href="#"
              ></a>
              <a
                class="icon-ft-qun a-move a-moveHover"
                title="尚融宝QQ群"
                target="_blank"
                href="#"
              ></a>
              <a
                class="icon-hdSprite icon-ft-email a-move a-moveHover mrn"
                title="阳光易贷email"
                target="_blank"
                href="mailto:xz@shangrongbao.com"
              ></a>
            </div>
          </dd>
        </dl>
      </div>
      <div class="ft-wap clearfix">
        <dl>
          <dt>官方二维码</dt>
          <dd>
            <span class="icon-ft-erweima">
              <img src="~/assets/images/code.png" style="display: inline;" />
            </span>
          </dd>
        </dl>
      </div>
    </div>
    <div class="ft-record">
      <div class="ft-approve clearfix">
        <a class="icon-approve approve-0 fadeIn-2s" target="_blank" href="#">
        </a>
        <a class="icon-approve approve-1 fadeIn-2s" target="_blank" href="#">
        </a>
        <a class="icon-approve approve-2 fadeIn-2s" target="_blank" href="#">
        </a>
        <a class="icon-approve approve-3 fadeIn-2s" target="_blank" href="#">
        </a>
      </div>
      <div class="ft-identity">
        ©2020 尚融宝 All rights reserved&nbsp;&nbsp;&nbsp;
        <span class="color-e6">|</span>&nbsp;&nbsp;&nbsp;
        北京市尚融宝投资管理有限公司&nbsp;&nbsp;&nbsp;
        <span class="color-e6">|</span>&nbsp;&nbsp;&nbsp;
        <a target="_blank" href="http://www.miitbeian.gov.cn/">
          京ICP备12345678号-1
        </a>
      </div>
    </div>
  </div>
</template>
