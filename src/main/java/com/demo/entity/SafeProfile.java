package com.demo.entity;

public class SafeProfile {
	
	private Profile safeProfile;
	private Long userId;
	private Long profileId;
	
	public SafeProfile() {
		
	}

	public SafeProfile(Profile safeProfile, Long userId, Long profileId) {
		this.safeProfile = safeProfile;
		this.userId = userId;
		this.profileId = profileId;
	}

	public Profile getSafeProfile() {
		return safeProfile;
	}

	public void setSafeProfile(Profile safeProfile) {
		this.safeProfile = safeProfile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	
}
