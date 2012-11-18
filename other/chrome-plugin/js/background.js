chrome.pageAction.onClicked.addListener(function(tab) {
    var url = 'http://grails-toolbox.cloudfoundry.com/user/addResource';
//    var url = 'http://localhost:8080/user/addResource';
    var data = {url: tab.url, comment: tab.title};

    $.ajax({
        url:url,
        type:'POST',
        data: (data),

        success:function (data) {
            chrome.pageAction.setIcon({tabId: tab.id, path: 'icons/added.png'});
        },

        error: function(e) {
            chrome.pageAction.setIcon({tabId: tab.id, path: 'icons/error.png'});
            console.log(e);
        }
    });
});

chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab) {
    chrome.pageAction.show(tabId);
});
