package khiemvuong.product_service.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("keyResolver") // Đặt tên Bean là "keyResolver" để khớp với application.yml
public class IPAddressKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(org.springframework.web.server.ServerWebExchange exchange) {
        // Lấy địa chỉ IP của client làm khóa
        return Mono.just(
            exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress()
        );
    }
}
