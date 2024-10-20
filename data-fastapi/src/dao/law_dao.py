import json

from src.dao.db_conn import DBConn


class LAW(DBConn):

    def insert_current_law_list(self,
                                main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                                current_history_code, law_name_korean, law_abbreviation_name, law_ID,
                                proclamation_date,
                                proclamation_number, revision_category_name, responsible_department_name,
                                responsible_department_code,
                                law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                                self_other_law_flag, law_detail_link
                                ):
        return self._call('current_law_list$insert',
                          [main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                           current_history_code, law_name_korean, law_abbreviation_name, law_ID, proclamation_date,
                           proclamation_number, revision_category_name, responsible_department_name,
                           responsible_department_code,
                           law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                           self_other_law_flag, law_detail_link])

    def insert_current_law_detail(self, current_law_list_id, law_id, proclamation_date, proclamation_number, language,
                                  law_type, law_type_code,
                                  law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                                  section, department_name, department_code, phone_number, enforcement_date,
                                  revision_type,
                                  has_table, is_proclaimed, contact_office, contact_number, article_number,
                                  article_status,
                                  article_effective_date, article_previous, article_next, article_changed,
                                  article_title,
                                  article_content, article_dict, addendum_dict, amendment_dict):
        return self._call('current_law_detail$insert',
                          [current_law_list_id, law_id, proclamation_date, proclamation_number, language, law_type,
                           law_type_code,
                           law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                           section, department_name, department_code, phone_number, enforcement_date, revision_type,
                           has_table, is_proclaimed, contact_office, contact_number, article_number, article_status,
                           article_effective_date, article_previous, article_next, article_changed, article_title,
                           article_content, json.dumps(article_dict), json.dumps(addendum_dict),
                           json.dumps(amendment_dict)])

    def insert_enforcement_law_list(self,
                                    main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                                    current_history_code, law_name_korean, law_abbreviation_name, law_ID,
                                    proclamation_date,
                                    proclamation_number, revision_category_name, responsible_department_name,
                                    responsible_department_code,
                                    law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                                    self_other_law_flag, law_detail_link
                                    ):
        return self._call('enforcement_law_list$insert',
                          [main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                           current_history_code, law_name_korean, law_abbreviation_name, law_ID, proclamation_date,
                           proclamation_number, revision_category_name, responsible_department_name,
                           responsible_department_code,
                           law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                           self_other_law_flag, law_detail_link])

    def insert_enforcement_law_detail(self, enforcement_law_list, law_id, proclamation_date, proclamation_number,
                                      language,
                                      law_type, law_type_code,
                                      law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                                      section, department_name, department_code, phone_number, enforcement_date,
                                      revision_type,
                                      has_table, is_proclaimed, contact_office, contact_number, article_number,
                                      article_status,
                                      article_effective_date, article_previous, article_next, article_changed,
                                      article_title,
                                      article_content, article_dict, addendum_dict, amendment_dict):
        return self._call('enforcement_law_detail$insert',
                          [enforcement_law_list, law_id, proclamation_date, proclamation_number, language, law_type,
                           law_type_code,
                           law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                           section, department_name, department_code, phone_number, enforcement_date, revision_type,
                           has_table, is_proclaimed, contact_office, contact_number, article_number, article_status,
                           article_effective_date, article_previous, article_next, article_changed, article_title,
                           article_content, json.dumps(article_dict), json.dumps(addendum_dict),
                           json.dumps(amendment_dict)])

    def insert_law_history_law_list(self,
                                    main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                                    current_history_code, law_name_korean, law_abbreviation_name, law_ID,
                                    proclamation_date,
                                    proclamation_number, revision_category_name, responsible_department_name,
                                    responsible_department_code,
                                    law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                                    self_other_law_flag, law_detail_link
                                    ):
        return self._call('law_history_law_list$insert',
                          [main_text_id, target, keyword, section, totalCnt, page, law_serial_number,
                           current_history_code, law_name_korean, law_abbreviation_name, law_ID, proclamation_date,
                           proclamation_number, revision_category_name, responsible_department_name,
                           responsible_department_code,
                           law_category_name, joint_decree_type, joint_proclamation_number, enforcement_date,
                           self_other_law_flag, law_detail_link])

    def insert_law_history_law_detail(self, law_history_law_list, law_id, proclamation_date, proclamation_number,
                                      language, law_type, law_type_code,
                                      law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                                      section, department_name, department_code, phone_number, enforcement_date,
                                      revision_type,
                                      has_table, is_proclaimed, contact_office, contact_number, article_number,
                                      article_status,
                                      article_effective_date, article_previous, article_next, article_changed,
                                      article_title,
                                      article_content, article_dict, addendum_dict, amendment_dict):
        return self._call('law_history_law_detail$insert',
                          [law_history_law_list, law_id, proclamation_date, proclamation_number, language, law_type,
                           law_type_code,
                           law_name_korean, law_name_chinese, law_abbreviation, title_change, is_korean_law,
                           section, department_name, department_code, phone_number, enforcement_date, revision_type,
                           has_table, is_proclaimed, contact_office, contact_number, article_number, article_status,
                           article_effective_date, article_previous, article_next, article_changed, article_title,
                           article_content, json.dumps(article_dict), json.dumps(addendum_dict),
                           json.dumps(amendment_dict)])

    def insert_case_law_list(self,
                             case_main_text_id, target, proclamation_number, keyword, section, total_count, page,
                             result_id,
                             case_serial_number, case_name, case_number, judgment_date, court_name, court_type_code,
                             case_type_name, case_type_code, judgment_type, judgment_result, case_detail_link
                             ):
        return self._call('case_law_list$insert',
                          [case_main_text_id, target, proclamation_number, keyword, section, total_count, page,
                           result_id,
                           case_serial_number, case_name, case_number, judgment_date, court_name, court_type_code,
                           case_type_name, case_type_code, judgment_type, judgment_result, case_detail_link])

    def insert_case_law_detail(self, case_law_list_id, case_name, case_number, judgment_date, judgment_result,
                               court_name,
                               court_type_code, case_type_name, case_type_code, judgment_type, key_issue, case_summary,
                               reference_laws, reference_cases, case_details, case_data):
        return self._call('case_law_detail$insert',
                          [case_law_list_id, case_name, case_number, judgment_date, judgment_result, court_name,
                           court_type_code, case_type_name, case_type_code, judgment_type, key_issue, case_summary,
                           reference_laws, reference_cases, case_details, case_data])
