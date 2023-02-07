import spock.lang.Specification

class SpecDemo extends Specification{
    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2
        when:
        int result = left + right
        then:
        result == 3
    }
    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]
        when:
        list.remove(0)
        then:
        list == [2, 3, 4]
    }
    def "plus two number"() {
        expect:
        a + b == c
        where:
        a | b | c
        1 | 2 | 3
        2 | 2 | 4
        3 | 2 | 6
    }
}
