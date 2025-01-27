/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drill.exec.store.jdbc.rules;

import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.plan.RelTrait;
import org.apache.calcite.rel.RelCollations;
import org.apache.calcite.sql.dialect.MssqlSqlDialect;
import org.apache.drill.exec.planner.common.DrillLimitRelBase;
import org.apache.drill.exec.store.enumerable.plan.DrillJdbcRuleBase;
import org.apache.drill.exec.store.jdbc.DrillJdbcConvention;

public class JdbcLimitRule extends DrillJdbcRuleBase.DrillJdbcLimitRule {
  private final DrillJdbcConvention convention;

  public JdbcLimitRule(RelTrait in, DrillJdbcConvention out) {
    super(in, out);
    this.convention = out;
  }

  @Override
  public boolean matches(RelOptRuleCall call) {
    DrillLimitRelBase limit = call.rel(0);
    if (super.matches(call)) {
      // MS SQL doesn't support either OFFSET or FETCH without ORDER BY.
      // But for the case of FETCH without OFFSET, Calcite generates TOP N
      // instead of FETCH, and it is supported by MS SQL.
      // So do not push down only the limit with both OFFSET and FETCH but without ORDER BY.
      return limit.getOffset() == null
        || !limit.getTraitSet().contains(RelCollations.EMPTY)
        || !(convention.getPlugin().getDialect() instanceof MssqlSqlDialect);
    }
    return false;
  }
}
