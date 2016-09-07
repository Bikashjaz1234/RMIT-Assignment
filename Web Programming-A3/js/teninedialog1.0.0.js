/*
 * teninedialog 1.0.0
 * Copyright (c) 2013 褰煎哺涔嬭繙  http://www.xnwai.com/
 * Date: 2013-06-03
 * 閽堝bootstrap妯℃€佸璇濇鐨勪簩娆″皝瑁呫€�
 */
(function($){
    $.fn.teninedialog = function(options){
        var defaults = {
            title:'鏍囬',
            content:'鍐呭',
            showCloseButton:true,//鏄剧ず鍏抽棴鎸夐挳
            otherButtons:[],//鍏朵粬鎸夐挳鏂囨湰锛屾牱寮忛粯璁�,["纭畾","鍙栨秷"]
            otherButtonStyles:[],//鍏朵粬鎸夐挳鐨勬牱寮忥紝['btn-primary','btn-primary'],bootstrap鎸夐挳鏍峰紡
            bootstrapModalOption:{},//榛樿鐨刡ootstrap妯℃€佸璇濇鍙傛暟
            dialogShow:function(){},//瀵硅瘽妗嗗嵆灏嗘樉绀轰簨浠�
            dialogShown:function(){},//瀵硅瘽妗嗗凡缁忔樉绀轰簨浠�
            dialogHide:function(){},//瀵硅瘽妗嗗嵆灏嗗叧闂�
            dialogHidden:function(){},//瀵硅瘽妗嗗凡缁忓叧闂簨浠�
            clickButton:function(sender,modal,index){}//閫変腑鎸夐挳鐨勫簭鍙凤紝鎺掗櫎鍏抽棴鎸夐挳銆俿ender:鎸夐挳jquery瀵硅薄锛宮odel:瀵硅瘽妗唈query瀵硅薄,index:鎸夐挳鐨勯『搴�,otherButtons鐨勬暟缁勪笅鏍�
        }
        var options = $.extend(defaults, options);
        var modalID='';

        //鐢熸垚涓€涓儫涓€鐨処D
        function getModalID(){
            var d = new Date();
            var vYear = d.getFullYear();
            var vMon = d.getMonth()+1;
            var vDay = d.getDate();
            var h = d.getHours();
            var m = d.getMinutes();
            var se = d.getSeconds();
            var sse=d.getMilliseconds();
            return 't_'+vYear+vMon+vDay+h+m+se+sse;
        }

        $.fn.extend({
            closeDialog:function(modal){
                var modalObj=modal;
                modalObj.modal('hide');
            }
        });

        return this.each(function(){
            var obj=$(this);
            modalID=getModalID();
            var tmpHtml='<div id="{ID}" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">脳</button><h3 id="myModalLabel">{title}</h3></div><div class="modal-body"><p>{body}</p></div><div class="modal-footer">{button}</div></div>';
            var buttonHtml='<button class="btn" data-dismiss="modal" aria-hidden="true">鍏抽棴</button>';
            if (!options.showCloseButton&&options.otherButtons.length>0) {buttonHtml='';};
            //鐢熸垚鎸夐挳
            var btnClass='cls-'+modalID;
            for(var i=0;i<options.otherButtons.length;i++){
                buttonHtml+='<button buttonIndex="'+i+'" class="'+btnClass+' btn '+options.otherButtonStyles[i]+'">'+options.otherButtons[i]+'</button>';
            }
            //鏇挎崲妯℃澘鏍囪
            tmpHtml=tmpHtml.replace(/{ID}/g,modalID).replace(/{title}/g,options.title).replace(/{body}/g,options.content).replace(/{button}/g,buttonHtml);
            obj.append(tmpHtml);

            var modalObj=$('#'+modalID);
            //缁戝畾鎸夐挳浜嬩欢,涓嶅寘鎷叧闂寜閽�
            $('.'+btnClass).click(function(){
                var index=$(this).attr('buttonIndex');
                options.clickButton($(this),modalObj,index);
            });
            //缁戝畾鏈韩鐨勪簨浠�
            modalObj.on('show', function () {
                options.dialogShow();
            });
            modalObj.on('shown', function () {
                options.dialogShown();
            });
            modalObj.on('hide', function () {
                options.dialogHide();
            });
            modalObj.on('hidden', function () {
                options.dialogHidden();
                modalObj.remove();
            });
            modalObj.modal(options.bootstrapModalOption);
        });

    };

    $.extend({
        teninedialog: function(options) {
            $("body").teninedialog(options);
        }
    });

})(jQuery);