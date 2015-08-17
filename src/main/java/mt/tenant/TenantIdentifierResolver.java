package mt.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver
{
    @Override
    public String resolveCurrentTenantIdentifier()
    {
        return TenantThreadLocal.getTenant();
    }

    @Override
    public boolean validateExistingCurrentSessions()
    {
        return false;
    }
}
