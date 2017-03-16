---
title: "Securing Drill Introduction"
date: 2017-03-16 01:22:49 UTC
parent: "Securing Drill"
---

Before connecting to a data source, you can configure Drill security features and [secure communication pathways]({{site.baseurl}}/docs/secure-communication-paths/) to a secure Drill cluster.  Security features include:

- **Authentication** - Drill supports user authentication to secure clusters with:
	- Kerberos. 
		See [Kerberos Authentication]({{site.baseurl}}/docs/configuring-kerberos-authentication/).
	- Username and password (with the Plain mechanism or a Custom Authenticator). See: 
		- [Plain Authentication]({{site.baseurl}}/docs/configuring-plain-authentication/)  
		- [Creating Custom Authenticators]({{site.baseurl}}/docs/creating-custom-authenticators)
	- Digest 
- **Authorization** - Drill supports restricting an authenticated user's capabilities.
		See [Configuring User Impersonation with Hive Authorization]({{site.baseurl}}/docs/configuring-user-impersonation-with-hive-authorization/).
- **Impersonation** - Drill executes queries on behalf of a client while performing the action requested by the client.
		See:  
			- [Configuring User Impersonation]({{site.baseurl}}/docs/configuring-user-impersonation/)  
			- [Configuring Inbound Impersonation]({{site.baseurl}}/docs/configuring-inbound-impersonation/)  
			- [Configuring User Impersonation with Hive Authorization]({{site.baseurl}}/docs/configuring-user-impersonation-with-hive-authorization/)  
- **Encryption** - Drill does not support encryption as of Drill 1.10.














 
