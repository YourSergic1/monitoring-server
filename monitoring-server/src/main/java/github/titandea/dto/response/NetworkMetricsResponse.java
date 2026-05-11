package github.titandea.dto.response;

import lombok.*;

/**
 * DTO для отправки данных о метриках соединений.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetworkMetricsResponse {
    private String interfaceName;

    private Long bytesSent;

    private Long bytesRecv;

    private Long packetsSent;

    private Long packetsRecv;

    private Long inErrors;

    private Long outErrors;

    private Long speed;

}
