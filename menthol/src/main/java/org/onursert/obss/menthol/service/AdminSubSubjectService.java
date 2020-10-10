package org.onursert.obss.menthol.service;

import org.onursert.obss.menthol.model.SubSubject;
import org.onursert.obss.menthol.model.Subject;
import org.onursert.obss.menthol.repository.SubSubjectRepository;
import org.onursert.obss.menthol.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminSubSubjectService {

    @Autowired
    private SubSubjectRepository subSubjectRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public String adminSubSubjectUpdate(String subjectId, String subSubjectId, String subSubjectName, String newSubSubjects) {
        String[] subSubjectIds = subSubjectId.split(",");
        String[] subSubjectNames = subSubjectName.split(",");
        for (int i = 0; i < subSubjectIds.length; i++) {
            if (subSubjectNames[i].equals("")) {
                return "redirect:/admin/subject/list?error=update";
            }

            SubSubject subSubject = subSubjectRepository.findById(Integer.valueOf(subSubjectIds[i])).orElse(null);
            subSubject.setName(subSubjectNames[i]);
            subSubjectRepository.save(subSubject);
        }

        String[] newSubSubjectList = newSubSubjects.split(",");
        Subject foundSubject = subjectRepository.findById(Integer.valueOf(subjectId)).orElse(null);
        if (foundSubject == null) {
            return "redirect:/admin/subject/list?error=update";
        }
        for (int i = 0; i < newSubSubjectList.length; i++) {
            if (newSubSubjectList[i].equals("")) {
                return "redirect:/admin/subject/list?error=update";
            }

            SubSubject subSubject = new SubSubject();
            subSubject.setName(newSubSubjectList[i]);
            subSubject.setSubject(foundSubject);
            subSubjectRepository.save(subSubject);
        }

        return "redirect:/admin/subject/list?success=update";
    }

    public String adminSubSubjectDelete(Integer id) {
        try {
            subSubjectRepository.deleteById(id);
        } catch (Exception e) {
            return "redirect:/admin/subject/list?error=delete";
        }
        return "redirect:/admin/subject/list?success=delete";
    }
}
