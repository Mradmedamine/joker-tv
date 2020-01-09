ALTER TABLE iptv_subscription ADD COLUMN period_in_months INT NOT NULL DEFAULT 12;
ALTER TABLE sharing_subscription ADD COLUMN period_in_months INT NOT NULL DEFAULT 12;
