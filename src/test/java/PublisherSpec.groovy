import org.example.Publisher
import org.example.Subscriber
import spock.lang.Specification
import spock.lang.Unroll

class PublisherSpec extends Specification {
    Publisher publisher = new Publisher()
    Subscriber subscriber = Mock()
    Subscriber subscriber2 = Mock()

    def setup() {
        publisher.subscribers << subscriber // << is a Groovy shorthand for List.add()
        publisher.subscribers << subscriber2
    }
    @Unroll
    def "should send messages to all subscribers"() {
        when:
        publisher.send("helo")

        then:
        1 * subscriber.receive("hello")
        1 * subscriber2.receive("hello")
    }
}