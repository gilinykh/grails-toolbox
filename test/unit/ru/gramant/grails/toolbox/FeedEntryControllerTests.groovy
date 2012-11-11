package ru.gramant.grails.toolbox



import org.junit.*
import grails.test.mixin.*

/**
 * FeedEntryControllerTests
 * A unit test class is used to test individual methods or blocks of code without considering the surrounding infrastructure
 */
@TestFor(FeedEntryController)
@Mock(FeedEntry)
class FeedEntryControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/feedEntry/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.feedEntryInstanceList.size() == 0
        assert model.feedEntryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.feedEntryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.feedEntryInstance != null
        assert view == '/feedEntry/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/feedEntry/show/1'
        assert controller.flash.message != null
        assert FeedEntry.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/feedEntry/list'


        populateValidParams(params)
        def feedEntry = new FeedEntry(params)

        assert feedEntry.save() != null

        params.id = feedEntry.id

        def model = controller.show()

        assert model.feedEntryInstance == feedEntry
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/feedEntry/list'


        populateValidParams(params)
        def feedEntry = new FeedEntry(params)

        assert feedEntry.save() != null

        params.id = feedEntry.id

        def model = controller.edit()

        assert model.feedEntryInstance == feedEntry
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/feedEntry/list'

        response.reset()


        populateValidParams(params)
        def feedEntry = new FeedEntry(params)

        assert feedEntry.save() != null

        // test invalid parameters in update
        params.id = feedEntry.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/feedEntry/edit"
        assert model.feedEntryInstance != null

        feedEntry.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/feedEntry/show/$feedEntry.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        feedEntry.clearErrors()

        populateValidParams(params)
        params.id = feedEntry.id
        params.version = -1
        controller.update()

        assert view == "/feedEntry/edit"
        assert model.feedEntryInstance != null
        assert model.feedEntryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/feedEntry/list'

        response.reset()

        populateValidParams(params)
        def feedEntry = new FeedEntry(params)

        assert feedEntry.save() != null
        assert FeedEntry.count() == 1

        params.id = feedEntry.id

        controller.delete()

        assert FeedEntry.count() == 0
        assert FeedEntry.get(feedEntry.id) == null
        assert response.redirectedUrl == '/feedEntry/list'
    }
}
