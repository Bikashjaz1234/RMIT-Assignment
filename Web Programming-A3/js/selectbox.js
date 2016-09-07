jQuery.fn.extend({
    selectbox: function (options) {
        return this.each(function () {
            new jQuery.SelectBox(this, options);
        });
    }
});
if (!window.console) {
    var console = {
        log: function (msg) {}
    }
}
jQuery.SelectBox = function (selectobj, options) {
    var opt = options || {};
    opt.inputClass = opt.inputClass || "selectbox";
    opt.containerClass = opt.containerClass || "selectbox-wrapper";
    opt.hoverClass = opt.hoverClass || "current";
    opt.currentClass = opt.selectedClass || "selected"
    opt.debug = opt.debug || false;
    var elm_id = selectobj.id;
    var active = -1;
    var inFocus = false;
    var hasfocus = 0;
    var $select = $(selectobj);
    var $container = setupContainer(opt);
    var $input = setupInput(opt);
    $select.hide().before($input).before($container);
    init();
    $input.click(function () {
        if (!inFocus) {
            $container.toggle();
        }
    }).focus(function () {
        if ($container.not(':visible')) {
            inFocus = true;
            $container.show();
        }
    }).keydown(function (event) {
        switch (event.keyCode) {
        case 38:
            event.preventDefault();
            moveSelect(-1);
            break;
        case 40:
            event.preventDefault();
            moveSelect(1);
            break;
        case 13:
            event.preventDefault();
            $('li.' + opt.hoverClass).trigger('click');
            break;
        case 27:
            hideMe();
            break;
        }
    }).blur(function () {
        if ($container.is(':visible') && hasfocus > 0) {
            if (opt.debug) console.log('container visible and has focus')
        } else {
            hideMe();
        }
    });

    function hideMe() {
        hasfocus = 0;
        $container.hide();
    }

    function init() {
        $container.append(getSelectOptions($input.attr('id'))).hide();
        var width = $input.css('width');
        $container.width(width);
    }

    function setupContainer(options) {
        var container = document.createElement("div");
        $container = $(container);
        $container.attr('id', elm_id + '_container');
        $container.addClass(options.containerClass);
        return $container;
    }

    function setupInput(options) {
        var input = document.createElement("input");
        var $input = $(input);
        $input.attr("id", elm_id + "_input");
        $input.attr("type", "text");
        $input.addClass(options.inputClass);
        $input.attr("autocomplete", "off");
        $input.attr("readonly", "readonly");
        $input.attr("tabIndex", $select.attr("tabindex"));
        return $input;
    }

    function moveSelect(step) {
        var lis = $("li", $container);
        if (!lis) return;
        active += step;
        if (active < 0) {
            active = 0;
        } else if (active >= lis.size()) {
            active = lis.size() - 1;
        }
        lis.removeClass(opt.hoverClass);
        $(lis[active]).addClass(opt.hoverClass);
    }

    function setCurrent() {   
        var li = $("li."+opt.currentClass, $container).get(0);
        var ar = (''+li.id).split('_');
        var el = ar[ar.length-1];

        if($select.val() != el) {
            if (opt.debug) console.log('trigger change : '+ $select.attr('id') + ' changed from ' + $select.val() + ' to ' + el);
            $select.val(el);
            $select.trigger('change');           
        }
       
        $input.val($(li).html());
        return true;
    }

    function getCurrentSelected() {
        return $select.val();
    }

    function getCurrentValue() {
        return $input.val();
    }

    function getSelectOptions(parentid) {
        var select_options = new Array();
        var ul = document.createElement('ul');
        $select.children('option').each(function () {
            var li = document.createElement('li');
            li.setAttribute('id', parentid + '_' + $(this).val());
            li.innerHTML = $(this).html();
            if ($(this).is(':selected')) {
                $input.val($(this).html());
                $(li).addClass(opt.currentClass);
            }
            ul.appendChild(li);
            $(li).mouseover(function (event) {
                hasfocus = 1;
                if (opt.debug) console.log('over on : ' + this.id);
                jQuery(event.target, $container).addClass(opt.hoverClass);
            }).mouseout(function (event) {
                hasfocus = -1;
                if (opt.debug) console.log('out on : ' + this.id);
                jQuery(event.target, $container).removeClass(opt.hoverClass);
            }).click(function (event) {
                var fl = $('li.' + opt.hoverClass, $container).get(0);
                if (opt.debug) console.log('click on :' + this.id);
                $('li.' + opt.currentClass).removeClass(opt.currentClass);
                $(this).addClass(opt.currentClass);
                setCurrent();
                hideMe();
            });
        });
        return ul;
    }
};