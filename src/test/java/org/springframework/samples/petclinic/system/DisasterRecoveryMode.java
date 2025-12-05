package org.springframework.samples.petclinic.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Simple holder for Disaster Recovery (DR) / Read-Only mode flag.
 *
 * - app.read-only=true 이면 DR(Read-Only) 모드 ON - app.read-only=false 이면 정상 R/W 모드
 */
@Component
public class DisasterRecoveryMode {

	private final boolean readOnly;

	public DisasterRecoveryMode(@Value("${app.read-only:false}") boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	/** 가독성을 위해 별칭 메서드 하나 더 */
	public boolean isActive() {
		return this.readOnly;
	}

}
