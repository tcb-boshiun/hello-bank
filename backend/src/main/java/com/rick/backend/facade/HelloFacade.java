package com.rick.backend.facade;

import jakarta.validation.constraints.NotBlank;

/**
 * Hello 模組的外部統一介面（Facade）。
 *
 * <p>
 * 此介面負責提供對 Hello 模組的標準存取方式，避免直接依賴內部實作，
 * 並可作為前端、Service 或其他模組呼叫的唯一入口。
 */
public interface HelloFacade {

    /**
     * 回傳系統預設問候語。
     *
     * @return 預設的問候字串，例如「Hello」。
     */
    String hello();

    /**
     * 回傳帶有回聲效果的訊息。
     *
     * @param message 傳入的消息字串，不可為 null。
     * @return 回傳附帶回聲的結果字串；若 message 為空字串，實作端應處理預設回傳格式。
     */
    String echo(@NotBlank String message);
}
