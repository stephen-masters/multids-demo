(function($) {

    window.Rule = Backbone.Model.extend({

    });

    window.Rules = Backbone.Collection.extend({
        model: Library,
        url: "/kbase/albums"
    });

})(jQuery);
